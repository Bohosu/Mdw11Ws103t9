/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import com.googlecode.objectify.Key;
import data.Competition;
import data.CompetitionDAO;
import data.Match;
import data.MatchDAO;
import data.Team;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ondrej
 */
public class CompetitionModel {

    public static List table(Long competition) {
        CompetitionDAO cdao = new CompetitionDAO();
        Competition c = cdao.get(competition);
        if(c.getTeams() == null) {
            return null;
        }
        Map<Long, TableRow> table = new HashMap();
        for(Key<Team> kt : c.getTeams()) {
            Long id = kt.getId();
            table.put(id, new TableRow(kt));
        }
        MatchDAO mdao = new MatchDAO();
        List<Match> lm = mdao.query().filter("competition", new Key(Competition.class, c.getId()))
                .filter("played", true).list();
        for(Match m : lm) {
            Long home = m.getHome_team().getId();
            Long away = m.getAway_team().getId();
            
            int gh = m.getGoals_home();
            int ga = m.getGoals_away();

            table.get(home).addGs(gh);
            table.get(home).addGo(ga);
            table.get(away).addGs(ga);
            table.get(away).addGo(gh);
            
            if(gh > ga) {
                table.get(home).addW();
                table.get(away).addL();
            } else if(gh < ga) {
                table.get(home).addL();
                table.get(away).addW();
            } else {
                table.get(home).addD();
                table.get(away).addD();
            }
        }
        List<TableRow> l = new ArrayList(table.values());
        TableComparator tc = new TableComparator();
        Collections.sort(l, tc);
        return l;
    }

    public static Map<Integer, List<Match>> schedule(Long competition) {
        MatchDAO mdao = new MatchDAO();
        Map<Integer, List<Match>> schedule = new HashMap();
        List<Match> matches = mdao.query().filter("competition", new Key(Competition.class, competition)).list();
        for(Match m : matches) {
            if(!schedule.containsKey(m.getRound())) {
                schedule.put(m.getRound(), new ArrayList());
            }
            schedule.get(m.getRound()).add(m);
        }
        return schedule;
    }

    public static Map<Integer, List<Match>> results(Long competition) {
        MatchDAO mdao = new MatchDAO();
        Map<Integer, List<Match>> schedule = new HashMap();
        List<Match> matches = mdao.query().filter("played", true).filter("competition", new Key(Competition.class, competition)).list();
        for(Match m : matches) {
            if(!schedule.containsKey(m.getRound())) {
                schedule.put(m.getRound(), new ArrayList());
            }
            schedule.get(m.getRound()).add(m);
        }
        return schedule;
    }

}

class TableComparator implements Comparator<TableRow> {

    @Override
    public int compare(TableRow m1, TableRow m2) {

        int p1 = m1.getP();
        int p2 = m2.getP();

        if (p1 < p2){
            return +1;
        } else if (p1 > p2){
            return -1;
        } else{
            return 0;
        }
    }
}
