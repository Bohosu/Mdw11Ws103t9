/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.front;

import com.googlecode.objectify.Key;
import data.CardDAO;
import data.Competition;
import data.CompetitionDAO;
import data.GoalDAO;
import data.Match;
import data.MatchDAO;
import data.NewsDAO;
import data.News;
import data.Player;
import data.PlayerDAO;
import data.Post;
import data.PostDAO;
import data.Season;
import data.SeasonDAO;
import data.Team;
import data.TeamDAO;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
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
public class MatchController {

    public static void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MatchDAO dao = new MatchDAO();
        PlayerDAO pdao = new PlayerDAO();
        GoalDAO gdao = new GoalDAO();
        CardDAO cadao = new CardDAO();
        TeamDAO tdao = new TeamDAO();
        CompetitionDAO cdao = new CompetitionDAO();
        SeasonDAO sdao = new SeasonDAO();
        String id = (String)request.getParameter("id");
        if(id != null) {
            Long match_id = Long.parseLong(id);
            Match m = dao.get(match_id);
            Competition c = cdao.get(m.getCompetition().getId());
            Season s = sdao.get(c.getSeason().getId());
            if(m.getPlayers() != null) {
                Map map = pdao.get(Arrays.asList(m.getPlayers()));
                request.setAttribute("players", map);
                request.setAttribute("squad", map.values());
                if(m.getGoals() != null) {
                    request.setAttribute("goals", gdao.get(Arrays.asList(m.getGoals())).values());
                }
                if(m.getCards() != null) {
                    request.setAttribute("cards", cadao.get(Arrays.asList(m.getCards())).values());
                }
            }
            Key<Team>[] kt = new Key[2];
            kt[0] = m.getAway_team();
            kt[1] = m.getHome_team();
            request.setAttribute("teams", tdao.get(Arrays.asList(kt)));
            request.setAttribute("match", m);
            request.setAttribute("cmp", c);
            request.setAttribute("season", s);
        }
    }

}
