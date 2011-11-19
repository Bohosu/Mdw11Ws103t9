/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.admin;

import data.Season;
import data.SeasonDAO;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ondrej
 */
public class SeasonsController {

    public static void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        SeasonDAO dao = new SeasonDAO();
        if(action == null) {
            request.setAttribute("data", dao.getAll());
        } else if(action.equals("delete")) {
            Long id = Long.parseLong(request.getParameter("id"));
            dao.delete(id);
            response.sendRedirect("/admin?section=seasons");
        } else if(action.equals("new") && request.getMethod().equals("POST")) {
            Set errors = new HashSet<String>();
            Season s = new Season();
            String year = request.getParameter("year");
            if(year == null || !year.matches("^[0-9]{4}$")) {
                errors.add("Rok musí být ve tvaru RRRR!");
            }
            if(errors.isEmpty()) {
                s.setYear(Integer.parseInt(year));
                dao.put(s);
                response.sendRedirect("/admin?section=seasons");
            } else {
                request.setAttribute("errors", errors);
            }
        } else if(action.equals("edit")) {
            Long id = Long.parseLong(request.getParameter("id"));
            Season s = dao.get(id);
            if(request.getMethod().equals("POST")) {
                Set errors = new HashSet<String>();
                String year = request.getParameter("year");
                if(year == null || !year.matches("^[0-9]{4}$")) {
                    errors.add("Rok musí být ve tvaru RRRR!");
                }
                if(errors.isEmpty()) {
                    s.setYear(Integer.parseInt(year));
                    dao.put(s);
                    response.sendRedirect("/admin?section=seasons");
                } else {
                    request.setAttribute("errors", errors);
                }
            }
            request.setAttribute("item", s);
        }
    }

}
