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
public class CompetitionDAO extends DAO<Competition> {

    static {
        ObjectifyService.register(Competition.class);
    }

    public CompetitionDAO() {
        super(Competition.class);
    }
}
