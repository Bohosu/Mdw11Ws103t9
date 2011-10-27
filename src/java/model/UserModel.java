/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data.User;
import data.UserDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ondrej
 */
public class UserModel {

    final static String SESSION = "user_logged_in";
    private static boolean loggedIn;
    public static User u;

    public static boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object o = session.getAttribute(SESSION);
        loggedIn = (o != null && o.equals(true));
        return loggedIn;
    }

    public static void login(String email, String password, HttpServletRequest request) {
        UserDAO dao = new UserDAO();
        User user = dao.ofy().query(User.class).filter("email", email).get();
        if(user != null && user.getPassword().equals(calculateHash(password))) {
            HttpSession session = request.getSession();
            session.setAttribute(SESSION, true);
            u = user;
            loggedIn = true;
        }
    }

    public static void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(SESSION, false);
        loggedIn = false;
    }

    public static String calculateHash(String pass) {
        return HashUtil.SHA1AsString(pass);
    }
}
