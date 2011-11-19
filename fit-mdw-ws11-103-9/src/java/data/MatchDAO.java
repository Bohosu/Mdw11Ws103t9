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
public class MatchDAO extends DAO<Match> {

    static {
        ObjectifyService.register(Match.class);
    }

    public MatchDAO() {
        super(Match.class);
    }
}
