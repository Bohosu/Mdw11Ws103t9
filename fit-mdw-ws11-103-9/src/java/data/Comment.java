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
public class Comment {

    @Id
    private Long id;
    private String text;
    Key<User> user;
    Key<News> news;
    private Date date;

    public Comment() {}

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Key<News> getNews() {
        return news;
    }

    public void setNews(Key<News> news) {
        this.news = news;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Key<User> getUser() {
        return user;
    }

    public void setUser(Key<User> user) {
        this.user = user;
    }

    
}
