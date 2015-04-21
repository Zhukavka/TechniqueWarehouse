package com.dashyl.servlet;

import com.dashyl.DAO.UserDAO;
import com.dashyl.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darya on 17.04.2015.
 **/
@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("static/jsp/auth.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.getRequestDispatcher("Static/jsp/order.jsp").forward(req, resp);
        String key = req.getParameter("key");
        String user = req.getParameter("user");
        if(key.equals("123")) {
            UserDAO userService = new UserDAO();
            List<User> users = userService.getAll();
            for(User obj: users){
                if(user.equals(obj.getName())) {
                    Cookie loginCookie = new Cookie("user", user);
                    //setting cookie to expiry in 12 hours
                    loginCookie.setMaxAge(60 * 60 * 12);
                    resp.addCookie(loginCookie);
                    resp.sendRedirect("available_prod");
                    break;
                }
            }
        } else if(key.equals("admin") && user.equals("admin")) {
            Cookie loginCookie = new Cookie("user", user);
            //setting cookie to expiry in 12 hours
            loginCookie.setMaxAge(60 * 60 * 12);
            resp.addCookie(loginCookie);
            resp.sendRedirect("available_prod");
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/auth");
            PrintWriter out = resp.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(req, resp);
        }
    }
}