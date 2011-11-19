/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rest;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import data.Competition;
import data.CompetitionDAO;
import data.Match;
import data.MatchDAO;
import data.Player;
import data.PlayerDAO;
import data.Season;
import data.SeasonDAO;
import data.Team;
import data.TeamDAO;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author bohosu
 */
@Path("/friendly")
public class Friendly {

    @DELETE
    @Path("/{id}")
    @Produces("text/plain")
    public Response deleteReservation(@PathParam("id") Long id) {
        MatchDAO dao = new MatchDAO();
        CompetitionDAO cdao = new CompetitionDAO();
        try {
            Match m = dao.get(id);Competition c = cdao.get(m.getCompetition().getId());
            if(c.getFriendly()) {
                dao.delete(m);
                if(dao.query().filter("competition", m.getCompetition()).count() == 0) {
                    cdao.delete(c);
                }
                if(cdao.query().filter("season", c.getSeason()).count() == 0) {
                    SeasonDAO sdao = new SeasonDAO();
                    sdao.delete(c.getSeason().getId());
                }
                return Response.status(200).build();
            } else {
                return Response.status(400).build();
            }
        } catch(NotFoundException e) {
            return Response.status(400).build();
        }
    }


    @POST
    @Consumes("application/xml")
    public Response setFriendly(InputStream is) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(is);
            Element root = doc.getDocumentElement();
            if(!root.getNodeName().equals("friendly")) {
                return Response.status(400).build();
            }
            NodeList name = root.getElementsByTagName("name");
            if(name == null || name.getLength() != 1 || !name.item(0).hasChildNodes() || name.item(0).getFirstChild().getNodeType() != Node.TEXT_NODE) {
                return Response.status(400).build();
            }
            TeamDAO tdao = new TeamDAO();
            String teamName = name.item(0).getTextContent();
            Key<Team> kt = tdao.query().filter("name", teamName).getKey();
            if(kt == null) {
                Team t = new Team();
                t.setName(teamName);
                kt = tdao.put(t);
            }
            NodeList date = root.getElementsByTagName("date");
            if(date == null || date.getLength() != 1 || !date.item(0).hasChildNodes() || date.item(0).getFirstChild().getNodeType() != Node.TEXT_NODE || !date.item(0).getTextContent().matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}")) {
                return Response.status(400).build();
            }
            MatchDAO mdao = new MatchDAO();
            Match m = new Match();
            m.setHome_team(tdao.query().filter("own", true).getKey());
            m.setAway_team(kt);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                Date d = df.parse(date.item(0).getTextContent());
                m.setDate(d);
                int year = d.getYear() + 1900;

                int month = d.getMonth() + 1;
                Key<Season> ks;
                if(month > 7) {
                    SeasonDAO sdao = new SeasonDAO();
                     ks = sdao.query().filter("year", year).getKey();
                    if(ks == null) {
                        Season s = new Season();
                        s.setYear(year);
                        ks = sdao.put(s);
                    }
                } else {
                    SeasonDAO sdao = new SeasonDAO();
                    ks = sdao.query().filter("year", year - 1).getKey();
                    if(ks == null) {
                        Season s = new Season();
                        s.setYear(year - 1);
                        ks = sdao.put(s);
                    }
                }
                CompetitionDAO cdao = new CompetitionDAO();
                String sname = "Přátelské zápasy";
                Key<Competition> kc = cdao.query().filter("season", ks).filter("name", sname).getKey();
                if(kc == null) {
                    Competition c = new Competition();
                    c.setName(sname);
                    c.setSeason(ks);
                    c.setFriendly(true);
                    kc = cdao.put(c);
                }
                m.setCompetition(kc);
            } catch (ParseException ex) {
                return Response.status(400).build();
            }
            Long mid = mdao.put(m).getId();
            return Response.created(URI.create("/" + mid)).build();
        } catch (ParserConfigurationException ex) {
            return Response.serverError().build();
        } catch (SAXException ex) {
            return Response.status(400).build();
        } catch (IOException ex) {
            return Response.status(400).build();
        }
    }

}
