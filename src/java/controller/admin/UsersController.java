/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import data.User;
import data.UserDAO;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserModel;

/**
 *
 * @author ondrej
 */
public class UsersController {

    public static void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        UserDAO dao = new UserDAO();
        if (action == null) {
            request.setAttribute("data", dao.getAll());
            request.setAttribute("hash", UserModel.calculateHash("ondra"));
        } else if (action.equals("delete")) {
            Long id = Long.parseLong(request.getParameter("id"));
            User u = dao.get(id);
            if(!u.isAdmin()) {
                dao.delete(id);
            }
            response.sendRedirect("/admin?section=users");
        } else if (action.equals("new") && request.getMethod().equals("POST")) {
            Set errors = new HashSet<String>();
            User s = new User();
            String name = request.getParameter("nickname");
            String pass = request.getParameter("password");
            String email = request.getParameter("email");
            String admin = request.getParameter("admin");
            if (name == null || name.isEmpty()) {
                errors.add("Jméno musí být vyplněno!");
            }
            if (pass == null || pass.isEmpty()) {
                errors.add("Heslo musí být vyplněno!");
            }
            if (email == null || email.isEmpty()) {
                errors.add("Email není správně vyplněn!");
            }
            if (errors.isEmpty()) {
                s.setNickname(name);
                s.setPassword(UserModel.calculateHash(pass));
                s.setEmail(email);
                s.setAdmin(admin != null);
                dao.put(s);
                response.sendRedirect("/admin?section=users");
            } else {
                request.setAttribute("errors", errors);
            }
        } else if (action.equals("edit")) {
            Long id = Long.parseLong(request.getParameter("id"));
            User s = dao.get(id);
            if (request.getMethod().equals("POST")) {
                Set errors = new HashSet<String>();
                String name = request.getParameter("nickname");
                String email = request.getParameter("email");
                String admin = request.getParameter("admin");
                if (name == null || name.isEmpty()) {
                    errors.add("Jméno musí být vyplněno!");
                }
                if (email == null || email.isEmpty()) {
                    errors.add("Email není správně vyplněn!");
                }
                if (errors.isEmpty()) {
                    s.setNickname(name);
                    s.setEmail(email);
                    s.setAdmin(admin != null);
                    dao.put(s);
                    response.sendRedirect("/admin?section=users");
                } else {
                    request.setAttribute("errors", errors);
                }
            }
            request.setAttribute("item", s);
        }
    }
}
