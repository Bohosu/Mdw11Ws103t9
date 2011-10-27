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
public class PostDAO extends DAO<Post> {

    static {
        ObjectifyService.register(Post.class);
    }

    public PostDAO() {
        super(Post.class);
    }
}
