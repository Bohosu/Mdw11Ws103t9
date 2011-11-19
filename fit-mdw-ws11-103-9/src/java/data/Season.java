/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import com.googlecode.objectify.annotation.Unindexed;
import javax.jdo.annotations.Unique;
import javax.persistence.Id;

/**
 *
 * @author ondrej
 */
public class Season {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Id
    private Long id;

    private int year;

    public Season() {
    }

    @Override
    public String toString() {
        return year + "/" + (year + 1);
    }

}
