/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import com.googlecode.objectify.Key;
import javax.persistence.Id;

/**
 *
 * @author ondrej
 */
public class Competition {

    @Id
    private Long id;
    private boolean friendly;

    public boolean getFriendly() {
        return friendly;
    }

    public void setFriendly(boolean friendly) {
        this.friendly = friendly;
    }

    @Override
    public String toString() {
        return "Competition{" + "id=" + id + "season=" + season + "teams=" + teams + "name=" + name + '}';
    }
    Key<Season> season;
    Key<Team>[] teams;
    private String name;

    public Competition() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Key<Season> getSeason() {
        return season;
    }

    public void setSeason(Key<Season> season) {
        this.season = season;
    }

    public Key<Team>[] getTeams() {
        return teams;
    }

    public void setTeams(Key<Team>[] teams) {
        this.teams = teams;
    }

}
