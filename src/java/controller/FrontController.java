/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.*;
import model.UserModel;

/**
 *
 * @author ondrej
 */
public class FrontController extends BaseController {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String logout = request.getParameter("logout");
        if(logout != null && Integer.parseInt(logout) == 1) {
            UserModel.logout(request);
            response.sendRedirect("/");
        }
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginEmail = request.getParameter("login_email");
        String loginPassword = request.getParameter("login_password");
        if(loginEmail != null && loginPassword != null) {
            UserModel.login(loginEmail, loginPassword, request);
            if(UserModel.isLoggedIn(request)) {
                response.sendRedirect("/");
            } else {
                request.setAttribute("error", "Přihlášení bylo neúspěšné!");
            }
        }
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String section = request.getParameter("section");
        if(section == null) {
            section = "news";
        }
        try {
            sectionRequest(request, response, section);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("section", section);
        request.setAttribute("login", UserModel.isLoggedIn(request));
        forward("/front.jsp", request, response);
    }

    protected void sectionRequest(HttpServletRequest request, HttpServletResponse response, String section) throws IOException, ClassNotFoundException {
        try {
            String s = "controller.front." + section.substring(0, 1).toUpperCase() + section.substring(1) + "Controller";
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter o = response.getWriter();
            Class c = Class.forName(s);
            Method m = c.getDeclaredMethod("process", HttpServletRequest.class, HttpServletResponse.class);
            m.invoke(null, request, response);
        } catch (Exception ex) {}

    }
}
