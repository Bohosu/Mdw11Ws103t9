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
public class GoalDAO extends DAO<Goal> {

    static {
        ObjectifyService.register(Goal.class);
    }

    public GoalDAO() {
        super(Goal.class);
    }
}
