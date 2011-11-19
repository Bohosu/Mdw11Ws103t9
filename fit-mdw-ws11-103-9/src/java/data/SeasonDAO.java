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
public class SeasonDAO extends DAO<Season> {

    static {
        ObjectifyService.register(Season.class);
    }

    public SeasonDAO() {
        super(Season.class);
    }
}
