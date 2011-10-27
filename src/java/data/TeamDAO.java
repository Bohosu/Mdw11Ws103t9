/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import com.googlecode.objectify.ObjectifyService;

/**
 *
 * @author ondrej
 */
public class TeamDAO extends DAO<Team> {

    static {
        ObjectifyService.register(Team.class);
    }

    public TeamDAO() {
        super(Team.class);
    }
}
