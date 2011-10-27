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
public class CommentDAO extends DAO<Comment> {

    static {
        ObjectifyService.register(Comment.class);
    }

    public CommentDAO() {
        super(Comment.class);
    }
}
