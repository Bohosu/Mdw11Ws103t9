/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.front;

import data.NewsDAO;
import data.News;
import data.User;
import data.UserDAO;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserModel;

/**
 *
 * @author ondrej
 */
public class RegisterController {

    public static void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getMethod().equals("POST")) {
            UserDAO dao = new UserDAO();
            Set errors = new HashSet<String>();
            String name = request.getParameter("reg_nickname");
            String pass = request.getParameter("reg_password");
            String email = request.getParameter("reg_email");
            if (name == null || name.isEmpty()) {
                errors.add("Jméno je povinné!");
            }
            if (pass == null || pass.isEmpty()) {
                errors.add("Heslo je povinné!");
            }
            if (email == null || !email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")) {
                errors.add("Email není ve správném formátu!");
            }
            if (errors.isEmpty()) {
                User u = new User();
                u.setAdmin(false);
                u.setEmail(email);
                u.setNickname(name);
                u.setPassword(UserModel.calculateHash(pass));
                dao.put(u);
                response.sendRedirect("/");
            } else {
                request.setAttribute("errors", errors);
            }
        }
    }
}
