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
public class Team {

    @Id
    private Long id;
    private boolean own;

    public boolean isOwn() {
        return own;
    }

    public boolean getOwn() {
        return own;
    }

    public void setOwn(boolean own) {
        this.own = own;
    }
    private String name;

    public Team() {}

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

    public String toString() {
        return name;
    }

}
