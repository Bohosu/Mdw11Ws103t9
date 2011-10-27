/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import com.googlecode.objectify.Key;
import data.Player;
import data.PlayerDAO;
import data.Post;
import data.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ondrej
 */
public class PlayersController {

    public static void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        PlayerDAO dao = new PlayerDAO();
        PostDAO pdao = new PostDAO();
        request.setAttribute("posts", pdao.getAll());
        if (action == null) {
            request.setAttribute("data", dao.getAll());
            request.setAttribute("posts", pdao.getAllFetched());
        } else if (action.equals("delete")) {
            Long id = Long.parseLong(request.getParameter("id"));
            dao.delete(id);
            response.sendRedirect("/admin?section=players");
        } else if (action.equals("new") && request.getMethod().equals("POST")) {
            Set errors = new HashSet<String>();
            Player s = new Player();
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String number = request.getParameter("number");
            String height = request.getParameter("height");
            String weight = request.getParameter("weight");
            String post = request.getParameter("post");
            if (name == null || name.isEmpty()) {
                errors.add("Jméno je povinné!");
            }
            if (surname == null || surname.isEmpty()) {
                errors.add("Příjmení je povinné!");
            }
            if (number == null || !number.matches("^[0-9]+$")) {
                errors.add("Číslo je ve špatném formátu!");
            }
            if (height != null && !height.matches("^[0-9]+$")) {
                errors.add("Výška je ve špatném formátu!");
            }
            if (weight != null && !weight.matches("^[0-9]+$")) {
                errors.add("Váha je ve špatném formátu!");
            }
            if (post == null || !post.matches("^[0-9]+$")) {
                errors.add("Pozice je ve špatném formátu!");
            }
            if (errors.isEmpty()) {
                s.setName(name);
                s.setSurname(surname);
                s.setNumber(Integer.parseInt(number));
                if(height != null) {
                    s.setHeight(Integer.parseInt(height));
                }
                if(weight != null) {
                    s.setWeight(Integer.parseInt(weight));
                }
                s.setPost(new Key(Post.class, Long.parseLong(post)));
                dao.put(s);
                response.sendRedirect("/admin?section=players");
            } else {
                request.setAttribute("errors", errors);
            }
        } else if (action.equals("edit")) {
            request.setAttribute("posts", pdao.getAll());
            Long id = Long.parseLong(request.getParameter("id"));
            Player s = dao.get(id);
            if (request.getMethod().equals("POST")) {
                Set errors = new HashSet<String>();

                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String number = request.getParameter("number");
                String post = request.getParameter("post");
                String height = request.getParameter("height");
                String weight = request.getParameter("weight");
                if (name == null || name.isEmpty()) {
                    errors.add("Jméno je povinné!");
                }
                if (surname == null || surname.isEmpty()) {
                    errors.add("Příjmení je povinné!");
                }
                if (number == null || !number.matches("^[0-9]+$")) {
                    errors.add("Číslo je ve špatném formátu!");
                }
                if (post == null || !post.matches("^[0-9]+$")) {
                    errors.add("Pozice je ve špatném formátu!");
                }
                if (height != null && !height.matches("^[0-9]+$")) {
                    errors.add("Výška je ve špatném formátu!");
                }
                if (weight != null && !weight.matches("^[0-9]+$")) {
                    errors.add("Váha je ve špatném formátu!");
                }
                if (errors.isEmpty()) {
                    s.setName(name);
                    s.setSurname(surname);
                    s.setNumber(Integer.parseInt(number));
                    if(height != null) {
                        s.setHeight(Integer.parseInt(height));
                    }
                    if(weight != null) {
                        s.setWeight(Integer.parseInt(weight));
                    }
                    s.setPost(new Key(Post.class, Long.parseLong(post)));
                    dao.put(s);
                    response.sendRedirect("/admin?section=players");
                } else {
                    request.setAttribute("errors", errors);
                }
            }
            request.setAttribute("item", s);
        }
    }
}
