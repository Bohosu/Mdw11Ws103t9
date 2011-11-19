/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.admin;

import data.NewsDAO;
import data.News;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ondrej
 */
public class NewsController {

    public static void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        NewsDAO dao = new NewsDAO();
        if(action == null) {
            request.setAttribute("data", dao.getAll());
        } else if(action.equals("delete")) {
            Long id = Long.parseLong(request.getParameter("id"));
            dao.delete(id);
            response.sendRedirect("/admin?section=news");
        } else if(action.equals("new") && request.getMethod().equals("POST")) {
            Set errors = new HashSet<String>();
            News s = new News();
            String name = request.getParameter("name");
            String text = request.getParameter("text");
                if(name == null || name.isEmpty()) {
                    errors.add("Titulek musí být vyplněn!");
                }
                if(text == null || text.isEmpty()) {
                    errors.add("Text musí být vyplněn!");
                }
            if(errors.isEmpty()) {
                s.setName(name);
                s.setDate(new Date());
                s.setText(text);
                dao.put(s);
                response.sendRedirect("/admin?section=news");
            } else {
                request.setAttribute("errors", errors);
            }
        } else if(action.equals("edit")) {
            Long id = Long.parseLong(request.getParameter("id"));
            News s = dao.get(id);
            if(request.getMethod().equals("POST")) {
                Set errors = new HashSet<String>();
                String name = request.getParameter("name");
                String text = request.getParameter("text");
                if(name == null || name.isEmpty()) {
                    errors.add("Titulek musí být vyplněn!");
                }
                if(text == null || text.isEmpty()) {
                    errors.add("Text musí být vyplněn!");
                }
                if(errors.isEmpty()) {
                    s.setName(name);
                    s.setText(text);
                    dao.put(s);
                    response.sendRedirect("/admin?section=news");
                } else {
                    request.setAttribute("errors", errors);
                }
            }
            request.setAttribute("item", s);
        }
    }

}
