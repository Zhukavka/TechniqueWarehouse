package com.dashyl.command.auth;

import com.dashyl.DAO.UserDAO;
import com.dashyl.command.ServletCommand;
import com.dashyl.entity.AvailableProduct;
import com.dashyl.entity.User;
import com.dashyl.util.DAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Darya on 12.05.2015.
 */
public class LoginCommand implements ServletCommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String key = request.getParameter("key");
        String user = request.getParameter("user");
        if(key.equals("123")) {
            UserDAO userService = new UserDAO();
            List<User> users = userService.getAll();
            for(User obj: users){
                if(user.equals(obj.getName())) {
                    Cookie loginCookie = new Cookie("user", user);
                    //setting cookie to expiry in 12 hours
                    loginCookie.setMaxAge(60 * 60 * 12);
                    response.addCookie(loginCookie);
                    break;
                }
            }
        } else if(key.equals("admin") && user.equals("admin")) {
            Cookie loginCookie = new Cookie("user", user);
            //setting cookie to expiry in 12 hours
            loginCookie.setMaxAge(60 * 60 * 12);
            response.addCookie(loginCookie);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/warehouse");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request, response);
        }
        List<AvailableProduct> products =  DAOFactory.getInstance().getAvailableProductDAO().getAll();
        request.setAttribute("products", products);
        response.sendRedirect("warehouse");
        return "";
    }
}
