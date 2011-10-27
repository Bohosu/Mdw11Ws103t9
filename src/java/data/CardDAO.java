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
public class CardDAO extends DAO<Card> {

    static {
        ObjectifyService.register(Card.class);
    }

    public CardDAO() {
        super(Card.class);
    }
}
