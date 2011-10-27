/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import data.Team;
import data.TeamDAO;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ondrej
 */
public class TeamsController {

    public static void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        TeamDAO dao = new TeamDAO();
        if (action == null) {
            request.setAttribute("data", dao.getAll());
        } else if (action.equals("delete")) {
            Long id = Long.parseLong(request.getParameter("id"));
            dao.delete(id);
            response.sendRedirect("/admin?section=teams");
        } else if (action.equals("new") && request.getMethod().equals("POST")) {
            Set errors = new HashSet<String>();
            Team s = new Team();
            String name = request.getParameter("name");
            String own = request.getParameter("own");
            if (name == null || name.isEmpty()) {
                errors.add("Název musí být vyplněn!");
            }
            if (errors.isEmpty()) {
                s.setName(name);
                s.setOwn(own != null);
                dao.put(s);
                response.sendRedirect("/admin?section=teams");
            } else {
                request.setAttribute("errors", errors);
            }
        } else if (action.equals("edit")) {
            Long id = Long.parseLong(request.getParameter("id"));
            Team s = dao.get(id);
            if (request.getMethod().equals("POST")) {
                Set errors = new HashSet<String>();
                String name = request.getParameter("name");
                String own = request.getParameter("own");
                if (name == null || name.isEmpty()) {
                    errors.add("Název musí být vyplněn!");
                }
                if (errors.isEmpty()) {
                    s.setName(name);
                    s.setOwn(own != null);
                    dao.put(s);
                    response.sendRedirect("/admin?section=teams");
                } else {
                    request.setAttribute("errors", errors);
                }
            }
            request.setAttribute("item", s);
        }
    }
}
