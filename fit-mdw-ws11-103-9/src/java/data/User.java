/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import com.googlecode.objectify.Key;
import javax.persistence.Id;

/**
 *
 * @author ondrej
 */
public class User {
    @Id
    private Long id;
    private String nickname;
    private String email;
    private String password;
    private boolean admin;

    public User() {}

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
