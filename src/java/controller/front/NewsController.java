/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.front;

import com.googlecode.objectify.Key;
import controller.admin.*;
import data.Comment;
import data.CommentDAO;
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
public class NewsController {

    public static void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        NewsDAO dao = new NewsDAO();
        request.setAttribute("data", dao.getAll());
        String id = (String)request.getParameter("id");
        if(id != null) {
            request.setAttribute("detail", dao.get(Long.parseLong(id)));
            CommentDAO cdao = new CommentDAO();
            UserDAO udao = new UserDAO();
            request.setAttribute("comments", cdao.query().filter("news", new Key(News.class, id)).list());
            request.setAttribute("users", udao.getAllFetched());
            if(request.getMethod().equals("POST")) {
                String text = request.getParameter("text");
                if(text == null || text.isEmpty()) {
                    request.setAttribute("error", "Musíte vyplnit text!");
                } else {
                    Comment c = new Comment();
                    c.setDate(new Date());
                    c.setNews(new Key(News.class, id));
                    c.setUser(new Key(User.class, UserModel.u.getId()));
                    c.setText(text);
                    cdao.put(c);
                    response.sendRedirect("?" + request.getQueryString());
                }
            }
        }
    }

}
