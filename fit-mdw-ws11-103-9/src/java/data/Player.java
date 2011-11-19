/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import com.googlecode.objectify.Key;
import java.util.Date;
import javax.persistence.Id;

/**
 *
 * @author ondrej
 */
public class Player {

    @Id
    private Long id;
    private String name;
    private String surname;
    private int number;
    private Date birthday;
    private int height;
    private int weight;
    Key<Post> post;

    public Player() {}

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Key<Post> getPost() {
        return post;
    }

    public void setPost(Key<Post> post) {
        this.post = post;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
