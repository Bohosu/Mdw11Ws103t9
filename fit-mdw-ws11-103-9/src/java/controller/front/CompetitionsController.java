/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.front;

import com.googlecode.objectify.Key;
import data.Competition;
import data.CompetitionDAO;
import data.Match;
import data.MatchDAO;
import data.Season;
import data.SeasonDAO;
import data.TeamDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CompetitionModel;

/**
 *
 * @author ondrej
 */
public class CompetitionsController {

    public static void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SeasonDAO sdao = new SeasonDAO();
        CompetitionDAO cdao = new CompetitionDAO();
        request.setAttribute("seasons", sdao.getAll());
        String season = request.getParameter("season");
        String cmp = request.getParameter("competition");
        String title = "Soutěže";
        if(cmp != null) {
            TeamDAO tdao = new TeamDAO();
            Competition c = cdao.get(Long.parseLong(cmp));
            season = "" + c.getSeason().getId();
            request.setAttribute("season", c.getSeason().getId());
            request.setAttribute("competition", c);
            request.setAttribute("teams", tdao.getAllFetched());
            String type = request.getParameter("type");
            MatchDAO mdao = new MatchDAO();
            if(type == null && !c.getFriendly()) {
                request.setAttribute("table", CompetitionModel.table(c.getId()));
            } else if((type == null && c.getFriendly()) || type.equals("schedule")) {
                Map<Integer, List<Match>> schedule = CompetitionModel.schedule(c.getId());
                List<Integer> rounds = new ArrayList<Integer>(schedule.keySet());
                Collections.sort(rounds);
                request.setAttribute("rounds", rounds);
                request.setAttribute("schedule", schedule);
            } else if(type.equals("stats")) {
            } else if(type.equals("results")) {
                Map<Integer, List<Match>> results = CompetitionModel.results(c.getId());
                List<Integer> rounds = new ArrayList<Integer>(results.keySet());
                Collections.sort(rounds);
                request.setAttribute("rounds", rounds);
                request.setAttribute("results", results);
            }
            title = c.getName() + " " + sdao.get(c.getSeason().getId());
        }
        if(season != null) {
            Long id = Long.parseLong(season);
            request.setAttribute("competitions", cdao.query().filter("season", new Key(Season.class, id)).list());
            request.setAttribute("season", id);
        }

        request.setAttribute("title", title);
    }

}
