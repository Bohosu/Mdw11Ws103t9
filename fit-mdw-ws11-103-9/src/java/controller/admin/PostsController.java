/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.admin;

import data.Post;
import data.PostDAO;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ondrej
 */
public class PostsController {

    public static void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        PostDAO dao = new PostDAO();
        if(action == null) {
            request.setAttribute("data", dao.getAll());
        } else if(action.equals("delete")) {
            Long id = Long.parseLong(request.getParameter("id"));
            dao.delete(id);
            response.sendRedirect("/admin?section=posts");
        } else if(action.equals("new") && request.getMethod().equals("POST")) {
            Set errors = new HashSet<String>();
            Post s = new Post();
            String name = request.getParameter("name");
            if(name == null || name.isEmpty()) {
                errors.add("Název pozice je povinný!");
            }
            if(errors.isEmpty()) {
                s.setName(name);
                dao.put(s);
                response.sendRedirect("/admin?section=posts");
            } else {
                request.setAttribute("errors", errors);
            }
        } else if(action.equals("edit")) {
            Long id = Long.parseLong(request.getParameter("id"));
            Post s = dao.get(id);
            if(request.getMethod().equals("POST")) {
                Set errors = new HashSet<String>();
            String name = request.getParameter("name");
            if(name == null || name.isEmpty()) {
                errors.add("Název pozice je povinný!");
            }
                if(errors.isEmpty()) {
                    s.setName(name);
                    dao.put(s);
                    response.sendRedirect("/admin?section=posts");
                } else {
                    request.setAttribute("errors", errors);
                }
            }
            request.setAttribute("item", s);
        }
    }

}
