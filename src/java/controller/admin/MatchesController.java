/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import com.googlecode.objectify.Key;
import data.Card;
import data.CardColor;
import data.CardDAO;
import data.Competition;
import data.CompetitionDAO;
import data.Goal;
import data.GoalDAO;
import data.Match;
import data.MatchDAO;
import data.Player;
import data.PlayerDAO;
import data.SeasonDAO;
import data.Team;
import data.TeamDAO;
import java.io.IOException;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ondrej
 */
public class MatchesController {

    public static void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String competition = request.getParameter("competition");
        CompetitionDAO cdao = new CompetitionDAO();
        SeasonDAO sdao = new SeasonDAO();
        request.setAttribute("seasons", sdao.getAllFetched());
        if (competition == null || !competition.matches("^\\d+$")) {
            request.setAttribute("competitions", cdao.getAll());
            if (request.getMethod().equals("POST")) {
                response.sendRedirect("/admin?section=matches&competition=" + request.getParameter("cmp"));
            }
        } else {
            String action = request.getParameter("action");
            MatchDAO dao = new MatchDAO();
            TeamDAO tdao = new TeamDAO();
            Long cid = Long.parseLong(request.getParameter("competition"));
            Competition c = cdao.get(cid);
            request.setAttribute("teams", tdao.ofy().get(c.getTeams() == null ? tdao.query().fetchKeys() : Arrays.asList(c.getTeams())).values());
            request.setAttribute("cmps", cdao.getAll());
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            if (action == null) {
                request.setAttribute("data", dao.query().filter("competition", new Key(Competition.class, cid)).list());
                request.setAttribute("teams", tdao.getAllFetched());
                request.setAttribute("cmps", cdao.getAllFetched());
            } else if (action.equals("delete")) {
                Long id = Long.parseLong(request.getParameter("id"));
                dao.delete(id);
                response.sendRedirect("/admin?section=matches&competition=" + competition);
            } else if (action.equals("new") && request.getMethod().equals("POST")) {
                Set errors = new HashSet<String>();
                Match s = new Match();
                String home = request.getParameter("home_team");
                String away = request.getParameter("away_team");
                String score = request.getParameter("score");
                String date = request.getParameter("date");
                String round = request.getParameter("round");
                String cmp = request.getParameter("competition");
                if (home == null || !home.matches("^\\d+$")) {
                    errors.add("Špatný formát domácího týmu!");
                }
                if (away == null || !away.matches("^\\d+$")) {
                    errors.add("Špatný formát domácího týmu!");
                }
                if (score != null && !score.isEmpty() && !score.matches("^\\d+:\\d+ \\(\\d+:\\d+\\)$")) {
                    errors.add("Špatný formát skóre!");
                }
                if (date == null || !date.matches("^\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2}$")) {
                    errors.add("Špatný formát data!");
                }
                if (round == null || !round.matches("^[0-9]+$")) {
                    errors.add("Špatný formát kola!");
                }
                if (cmp == null || !cmp.matches("^\\d+$")) {
                    errors.add("Špatný formát soutěže!");
                }
                if (errors.isEmpty()) {
                    s.setHome_team(new Key(Team.class, Long.parseLong(home)));
                    s.setAway_team(new Key(Team.class, Long.parseLong(away)));
                    s.setCompetition(new Key(Competition.class, Long.parseLong(cmp)));
                    s.setRound(Integer.parseInt(round));
                    try {
                        s.setDate(df.parse(date));
                    } catch (ParseException ex) {
                        Logger.getLogger(MatchesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (!score.isEmpty()) {
                        String[] sc = score.replaceAll("[()]", "").split("[ :]");
                        s.setGoals_home(Integer.parseInt(sc[0]));
                        s.setGoals_ht_home(Integer.parseInt(sc[2]));
                        s.setGoals_away(Integer.parseInt(sc[1]));
                        s.setGoals_ht_away(Integer.parseInt(sc[3]));
                        s.setPlayed(true);
                    }
                    dao.put(s);
                    response.sendRedirect("/admin?section=matches&competition=" + competition);
                } else {
                    request.setAttribute("errors", errors);
                }
            } else if (action.equals("edit")) {
                Long id = Long.parseLong(request.getParameter("id"));
                Match s = dao.get(id);
                if (request.getMethod().equals("POST")) {
                    Set errors = new HashSet<String>();
                    String home = request.getParameter("home_team");
                    String away = request.getParameter("away_team");
                    String score = request.getParameter("score");
                    String date = request.getParameter("date");
                    String round = request.getParameter("round");
                    String cmp = request.getParameter("competition");
                    if (home == null || !home.matches("^\\d+$")) {
                        errors.add("Špatný formát domácího týmu!");
                    }
                    if (away == null || !away.matches("^\\d+$")) {
                        errors.add("Špatný formát domácího týmu!");
                    }
                    if (score != null && !score.isEmpty() && !score.matches("^\\d+:\\d+ \\(\\d+:\\d+\\)$")) {
                        errors.add("Špatný formát skóre!");
                    }
                    if (date == null || !date.matches("^\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2}$")) {
                        errors.add("Špatný formát data!");
                    }
                    if (round == null || !round.matches("^[0-9]+$")) {
                        errors.add("Špatný formát kola!");
                    }
                    if (cmp == null || !cmp.matches("^\\d+$")) {
                        errors.add("Špatný formát soutěže!");
                    }
                    if (errors.isEmpty()) {
                        s.setHome_team(new Key(Team.class, Long.parseLong(home)));
                        s.setAway_team(new Key(Team.class, Long.parseLong(away)));
                        s.setCompetition(new Key(Competition.class, Long.parseLong(cmp)));
                        s.setRound(Integer.parseInt(round));
                        try {
                            s.setDate(df.parse(date));
                        } catch (ParseException ex) {
                            Logger.getLogger(MatchesController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (!score.isEmpty()) {
                            String[] sc = score.replaceAll("[()]", "").split("[ :]");
                            s.setGoals_home(Integer.parseInt(sc[0]));
                            s.setGoals_ht_home(Integer.parseInt(sc[2]));
                            s.setGoals_away(Integer.parseInt(sc[1]));
                            s.setGoals_ht_away(Integer.parseInt(sc[3]));
                            s.setPlayed(true);
                        } else {
                            s.setPlayed(false);
                        }
                        dao.put(s);
                        response.sendRedirect("/admin?section=matches&competition=" + competition);
                    } else {
                        request.setAttribute("errors", errors);
                    }
                }
                request.setAttribute("item", s);
                request.setAttribute("itemDate", df.format(s.getDate()));
                request.setAttribute("itemScore", s.getGoals_home() + ":" + s.getGoals_away() + " (" + s.getGoals_ht_home() + ":" + s.getGoals_ht_away() + ")");
            } else if (action.equals("squad")) {
                Long id = Long.parseLong(request.getParameter("id"));
                PlayerDAO pdao = new PlayerDAO();
                List<Player> l = pdao.getAll();
                Match m = dao.get(id);
                request.setAttribute("players", l);
                if (m.getPlayers() != null) {
                    Map<Long, Boolean> map = new HashMap();
                    for (Key<Player> kp : m.getPlayers()) {
                        map.put(kp.getId(), true);
                    }
                    request.setAttribute("key_players", map);
                }
                if (request.getMethod().equals("POST")) {
                    Set<Key<Player>> r = new HashSet();
                    for (Player p : l) {
                        if (request.getParameter("player[" + p.getId() + "]") != null) {
                            r.add(new Key(Player.class, p.getId()));
                        }
                    }
                    Key<Player>[] k = new Key[r.size()];
                    m.setPlayers(r.toArray(k));
                    dao.put(m);
                    response.sendRedirect("/admin?section=matches&competition=" + competition);
                }
            } else if (action.equals("goals")) {
                Long id = Long.parseLong(request.getParameter("id"));
                PlayerDAO pdao = new PlayerDAO();
                Match m = dao.get(id);
                request.setAttribute("players", pdao.get(Arrays.asList(m.getPlayers())).values());
                int goals = 0;
                if (tdao.get(m.getHome_team().getId()).isOwn()) {
                    goals = m.getGoals_home();
                } else if (tdao.get(m.getAway_team().getId()).isOwn()) {
                    goals = m.getGoals_away();
                }
                request.setAttribute("goals", goals);
                GoalDAO gdao = new GoalDAO();
                if (m.getGoals() != null) {
                    Goal[] g = new Goal[goals];
                    int i = 0;
                    for (Key<Goal> kg : m.getGoals()) {
                        g[i] = gdao.get(kg.getId());
                        i++;
                    }
                    for (int j = m.getGoals().length; j < goals; j++) {
                        g[j] = null;
                    }
                    request.setAttribute("g", g);
                }
                if (request.getMethod().equals("POST")) {
                    if(m.getGoals() != null) {
                        gdao.delete(Arrays.asList(m.getGoals()));
                    }
                    Key<Goal>[] new_goals_keys = new Key[goals];
                    Goal[] new_goals = new Goal[goals];
                    Set<String> errors = new HashSet<String>();
                    for (int i = 0; i < goals; i++) {
                        String sc = request.getParameter("scorer[" + (i + 1) + "]");
                        String as = request.getParameter("asistent[" + (i + 1) + "]");
                        String min = request.getParameter("minute[" + (i + 1) + "]");
                        if (sc == null || !sc.matches("^(?:\\-1|\\d+)$")) {
                            errors.add("Střelec je vyplněn špatně!");
                        }
                        if (as == null || !as.matches("^(?:\\-1|\\d+)$")) {
                            errors.add("Asistent je vyplněn špatně!");
                        }
                        if (min != null && !min.isEmpty() && !min.matches("^\\d+$")) {
                            errors.add("Minuta je vyplněna špatně!");
                        }
                        if (errors.isEmpty()) {
                            Goal gt = new Goal();
                            Long scorer = Long.parseLong(sc);
                            Long asist = Long.parseLong(as);
                            if (scorer > 0) {
                                gt.setScorer(new Key(Player.class, scorer));
                            }
                            if (asist > 0) {
                                gt.setAsistent(new Key(Player.class, asist));
                            }
                            if (min != null && !min.isEmpty()) {
                                gt.setMinute(Integer.parseInt(min));
                            }
                            new_goals[i] = gt;
                        } else {
                            continue;
                        }
                    }
                    if (errors.isEmpty()) {
                        for (int i = 0; i < new_goals.length; i++) {
                            new_goals_keys[i] = gdao.put(new_goals[i]);
                        }
                        m.setGoals(new_goals_keys);
                        dao.put(m);
                        response.sendRedirect("/admin?section=matches&competition=" + competition);
                    } else {
                        request.setAttribute("errors", errors);
                    }
                }
            } else if (action.equals("cards")) {
                Long id = Long.parseLong(request.getParameter("id"));
                Match m = dao.get(id);
                PlayerDAO pdao = new PlayerDAO();
                CardDAO cadao = new CardDAO();
                if (request.getMethod().equals("POST")) {
                    String num = request.getParameter("num");
                    if ((num == null || !num.matches("^\\d+$")) && request.getParameter("num_preserve") != null) {
                        if(m.getCards() != null) {
                            cadao.delete(Arrays.asList(m.getCards()));
                        }
                        int card_num = Integer.parseInt(request.getParameter("num_preserve"));
                        Key<Card>[] new_card_keys = new Key[card_num];
                        Card[] new_cards = new Card[card_num];
                        Set<String> errors = new HashSet<String>();
                        for (int i = 0; i < card_num; i++) {
                            String pl = request.getParameter("card[" + (i + 1) + "]");
                            String type = request.getParameter("type[" + (i + 1) + "]");
                            if (pl == null || !pl.matches("^\\d+$")) {
                                errors.add("Hráč je vyplněn špatně!");
                            }
                            if (type == null || !type.matches("^(y|r)$")) {
                                errors.add("Typ je vyplněn špatně!");
                            }
                            if (errors.isEmpty()) {
                                Card card = new Card();
                                card.setPlayer(new Key(Player.class, Long.parseLong(pl)));
                                card.setType(type.equals("y") ? CardColor.YELLOW : CardColor.RED);
                                new_cards[i] = card;
                            } else {
                                continue;
                            }
                        }
                        if (errors.isEmpty()) {
                            for (int i = 0; i < new_cards.length; i++) {
                                new_card_keys[i] = cadao.put(new_cards[i]);
                            }
                            m.setCards(new_card_keys);
                            dao.put(m);
                            response.sendRedirect("/admin?section=matches&competition=" + competition);
                        } else {
                            request.setAttribute("errors", errors);
                            request.setAttribute("num_def", 0);
                        }
                    } else {
                        int n = Integer.parseInt(num);
                        if (n < 1) {
                            response.sendRedirect("/admin?section=matches&competition=" + competition);
                        } else {
                            request.setAttribute("players", pdao.get(Arrays.asList(m.getPlayers())).values());
                            request.setAttribute("num", n);

                            if (m.getGoals() != null) {
                                Card[] cards = new Card[m.getCards().length];
                                int i = 0;
                                for (Key<Card> kc : m.getCards()) {
                                    cards[i] = cadao.get(kc.getId());
                                    i++;
                                }
                                request.setAttribute("cards", cards);
                            }
                        }
                    }
                } else {
                    request.setAttribute("num_def", (m.getCards() == null ? 0 : m.getCards().length));
                }
            }
        }
    }
}
