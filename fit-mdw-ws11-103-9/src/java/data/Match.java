/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import com.googlecode.objectify.Key;
import java.util.Date;
import javax.persistence.Id;

/**
 *
 * @author ondrej
 */
public class Match {

    @Id
    private Long id;

    private int goals_home;
    private int goals_away;
    private int goals_ht_home;
    private int goals_ht_away;

    private int round;

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    Key<Competition> competition;

    public Key<Competition> getCompetition() {
        return competition;
    }

    public void setCompetition(Key<Competition> competition) {
        this.competition = competition;
    }

    Key<Team> home_team;
    Key<Team> away_team;
    
    Key<Goal>[] goals;
    Key<Card>[] cards;
    Key<Player>[] players;

    private Date date;

    private boolean played;

    public Match() {}

    public Key<Team> getAway_team() {
        return away_team;
    }

    public void setAway_team(Key<Team> away_team) {
        this.away_team = away_team;
    }

    public Key<Card>[] getCards() {
        return cards;
    }

    public void setCards(Key<Card>[] cards) {
        this.cards = cards;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Key<Goal>[] getGoals() {
        return goals;
    }

    public void setGoals(Key<Goal>[] goals) {
        this.goals = goals;
    }

    public int getGoals_away() {
        return goals_away;
    }

    public void setGoals_away(int goals_away) {
        this.goals_away = goals_away;
    }

    public int getGoals_home() {
        return goals_home;
    }

    public void setGoals_home(int goals_home) {
        this.goals_home = goals_home;
    }

    public int getGoals_ht_away() {
        return goals_ht_away;
    }

    public void setGoals_ht_away(int goals_ht_away) {
        this.goals_ht_away = goals_ht_away;
    }

    public int getGoals_ht_home() {
        return goals_ht_home;
    }

    public void setGoals_ht_home(int goals_ht_home) {
        this.goals_ht_home = goals_ht_home;
    }

    public Key<Team> getHome_team() {
        return home_team;
    }

    public void setHome_team(Key<Team> home_team) {
        this.home_team = home_team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public Key<Player>[] getPlayers() {
        return players;
    }

    public void setPlayers(Key<Player>[] players) {
        this.players = players;
    }

    
}
