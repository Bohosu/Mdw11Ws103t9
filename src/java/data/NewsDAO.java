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
public class NewsDAO extends DAO<News> {

    static {
        ObjectifyService.register(News.class);
    }

    public NewsDAO() {
        super(News.class);
    }
}
