/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import javax.persistence.Id;

/**
 *
 * @author ondrej
 */
public class Post {

    @Id
    private Long id;
    private String name;

    public Post() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
