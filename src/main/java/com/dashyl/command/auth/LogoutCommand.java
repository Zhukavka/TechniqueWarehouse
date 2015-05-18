package com.dashyl.command.auth;

import com.dashyl.command.ServletCommand;
import com.dashyl.entity.AvailableProduct;
import com.dashyl.util.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Darya on 12.05.2015.
 */
public class LogoutCommand implements ServletCommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        Cookie[] cookies = request.getCookies();
        Cookie loginCookie = null;
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("user")){
                    loginCookie = cookie;
                    break;
                }
            }
        }
        if(loginCookie != null){
            loginCookie.setMaxAge(0);
            response.addCookie(loginCookie);
        }
        List<AvailableProduct> products =  DAOFactory.getInstance().getAvailableProductDAO().getAll();
        request.setAttribute("products", products);
        response.sendRedirect("warehouse");
        return "";
    }
}
