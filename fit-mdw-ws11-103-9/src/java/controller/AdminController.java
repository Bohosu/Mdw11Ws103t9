/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.*;
import model.UserModel;

/**
 *
 * @author ondrej
 */
public class AdminController extends BaseController {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String logout = request.getParameter("logout");
        if(logout != null && Integer.parseInt(logout) == 1) {
            UserModel.logout(request);
            response.sendRedirect("/admin");
        }
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginEmail = request.getParameter("login_email");
        String loginPassword = request.getParameter("login_password");
        if(loginEmail != null && loginPassword != null) {
            UserModel.login(loginEmail, loginPassword, request);
            if(UserModel.isLoggedIn(request) && UserModel.u.isAdmin()) {
                response.sendRedirect("/admin");
            } else {
                request.setAttribute("error", "Přihlášení bylo neúspěšné!");
            }
        }
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String view = "login.jsp";
        if (UserModel.isLoggedIn(request) && UserModel.u.isAdmin()) {
            view = "admin.jsp";
        }
        request.setAttribute("view", view);
        String section = request.getParameter("section");
        if(section != null) {
            try {
                sectionRequest(request, response, section);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        forward("/admin.jsp", request, response);
    }

    protected void sectionRequest(HttpServletRequest request, HttpServletResponse response, String section) throws IOException, ClassNotFoundException {
        try {
            String s = "controller.admin." + section.substring(0, 1).toUpperCase() + section.substring(1) + "Controller";
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter o = response.getWriter();
            Class c = Class.forName(s);
            Method m = c.getDeclaredMethod("process", HttpServletRequest.class, HttpServletResponse.class);
            m.invoke(null, request, response);
        } catch (Exception ex) {}

    }
}
