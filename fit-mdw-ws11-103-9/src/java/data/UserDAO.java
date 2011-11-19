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
public class UserDAO extends DAO<User> {

    static {
        ObjectifyService.register(User.class);
    }

    public UserDAO() {
        super(User.class);
    }
}
