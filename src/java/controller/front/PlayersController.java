/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.front;

import data.NewsDAO;
import data.News;
import data.Player;
import data.PlayerDAO;
import data.Post;
import data.PostDAO;
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
public class PlayersController {

    public static void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PlayerDAO dao = new PlayerDAO();
        PostDAO pdao = new PostDAO();
        request.setAttribute("data", dao.getAll());
        request.setAttribute("posts", pdao.getAllFetched());
        String id = (String)request.getParameter("id");
        if(id != null) {
            Player p = dao.get(Long.parseLong(id));
            request.setAttribute("detail", p);
        }
    }

}
