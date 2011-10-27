/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.admin;

import com.googlecode.objectify.Key;
import data.Competition;
import data.CompetitionDAO;
import data.Season;
import data.SeasonDAO;
import data.Team;
import data.TeamDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ondrej
 */
public class CompetitionsController {

    public static void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        CompetitionDAO dao = new CompetitionDAO();
        SeasonDAO sdao = new SeasonDAO();
        request.setAttribute("seasons", sdao.getAll());
        if(action == null) {
            request.setAttribute("data", dao.getAll());
            request.setAttribute("seasons", sdao.getAllFetched());
        } else if(action.equals("delete")) {
            Long id = Long.parseLong(request.getParameter("id"));
            dao.delete(id);
            response.sendRedirect("/admin?section=competitions");
        } else if(action.equals("teams")) {
            Long id = Long.parseLong(request.getParameter("id"));
            TeamDAO tdao = new TeamDAO();
            List<Team> l = tdao.getAll();
            Competition c = dao.get(id);
            request.setAttribute("teams", l);
            if(c.getTeams() != null) {
                Map<Long, Boolean> m = new HashMap();
                for(Key<Team> kt : c.getTeams()) {
                    m.put(kt.getId(), true);
                }
                request.setAttribute("key_teams", m);
            }
            if(request.getMethod().equals("POST")) {
                Set<Key<Team>> r = new HashSet();
                for(Team t : l) {
                    if(request.getParameter("team[" + t.getId() + "]") != null) {
                        r.add(new Key(Team.class, t.getId()));
                    }
                }
                Key<Team>[] k = new Key[r.size()];
                c.setTeams(r.toArray(k));
                dao.put(c);
                response.sendRedirect("/admin?section=competitions");
            }
        } else if(action.equals("new") && request.getMethod().equals("POST")) {
            Set errors = new HashSet<String>();
            Competition s = new Competition();
            String name = request.getParameter("name");
            String season = request.getParameter("season");
            if(name == null || name.isEmpty()) {
                errors.add("Název soutěže je povinný!");
            }
            if(season == null || !season.matches("^[0-9]+$")) {
                errors.add("Špatný formát sezóny!");
            }
            if(errors.isEmpty()) {
                s.setName(name);
                s.setSeason(new Key(Season.class, Long.parseLong(season)));
                dao.put(s);
                response.sendRedirect("/admin?section=competitions");
            } else {
                request.setAttribute("errors", errors);
            }
        } else if(action.equals("edit")) {
            Long id = Long.parseLong(request.getParameter("id"));
            Competition s = dao.get(id);
            if(request.getMethod().equals("POST")) {
            Set errors = new HashSet<String>();
            String name = request.getParameter("name");
            String season = request.getParameter("season");
            if(name == null || name.isEmpty()) {
                errors.add("Název soutěže je povinný!");
            }
            if(season == null || !season.matches("^[0-9]+$")) {
                errors.add("Špatný formát sezóny!");
            }
                if(errors.isEmpty()) {
                s.setName(name);
                s.setSeason(new Key(Season.class, Long.parseLong(season)));
                    dao.put(s);
                    response.sendRedirect("/admin?section=competitions");
                } else {
                    request.setAttribute("errors", errors);
                }
            }
            request.setAttribute("item", s);
        }
    }

}
