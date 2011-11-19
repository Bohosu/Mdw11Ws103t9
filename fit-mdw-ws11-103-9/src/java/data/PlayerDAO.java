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
public class PlayerDAO extends DAO<Player> {

    static {
        ObjectifyService.register(Player.class);
    }

    public PlayerDAO() {
        super(Player.class);
    }
}
