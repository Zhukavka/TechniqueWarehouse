package com.dashyl.servlet;

import com.dashyl.OrderFactory;
import com.dashyl.command.auth.LogoutCommand;
import com.dashyl.command.manager.CommandFactory;
import com.dashyl.entity.AvailableProduct;
import com.dashyl.entity.Client;
import com.dashyl.entity.Order;
import com.dashyl.util.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Darya on 17.04.2015.
 **/


@MultipartConfig(location = "C:\\warehouse")//это потом в конфиг вынести
public class MainServlet extends HttpServlet {

    public MainServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    /*@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processGetRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processPostRequest(req, resp);
    }

    protected String getUsernameFromCookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("user")) return cookie.getValue();
            }
        }
        return null;
    }

    protected void processGetRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = getUsernameFromCookie(req);
        req.setAttribute("username", username);

        String event = req.getParameter("event");
        List<AvailableProduct> products =  DAOFactory.getInstance().getAvailableProductDAO().getAll();
        req.setAttribute("products", products);
        String message = req.getParameter("message");
        if(message != null) {
            req.setAttribute("message", processError(message));
        }

        if(username != null && !username.isEmpty()) {
            List<Order> orders = DAOFactory.getInstance().getOrderDAO().getAll();
            req.setAttribute("orders", orders);
            List<Client> clients = DAOFactory.getInstance().getClientDAO().getAll();
            req.setAttribute("clients", clients);
            Order order = OrderFactory.getInstance().getOrder(username);
            req.setAttribute("order", order);
            if(order != null)
                req.setAttribute("totalPrice", order.getCost());
        }

        if(event == null){
            req.getRequestDispatcher("static/jsp/mainpage.jsp").forward(req, resp);
        } else if(event.equals("auth")) {
            req.getRequestDispatcher("static/jsp/auth.jsp").forward(req, resp);
        } else if(event.equals("all_orders")) {
            req.getRequestDispatcher("static/jsp/all_orders.jsp").forward(req, resp);
        } else if(event.equals("choose_client")) {
            req.getRequestDispatcher("static/jsp/choose_client.jsp").forward(req, resp);
        } else if(event.equals("clients")) {
            req.getRequestDispatcher("static/jsp/clients.jsp").forward(req, resp);
        } else if(event.equals("new_client")){
            req.getRequestDispatcher("static/jsp/new_client.jsp").forward(req, resp);
        } else if(event.equals("new_user")) {
            req.getRequestDispatcher("static/jsp/new_user.jsp").forward(req, resp);
        } else if(event.equals("order")) {
            req.getRequestDispatcher("static/jsp/order.jsp").forward(req, resp);
        } else if(event.equals("add_products")){
            req.getRequestDispatcher("static/jsp/add_products.jsp").forward(req, resp);
        }else if (event.equals("logout")) {
            new LogoutCommand().execute(req,resp);
            return;
        } else if(event.equals("findProduct")) {
            req.getRequestDispatcher("static/jsp/mainpage.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("static/jsp/mainpage.jsp").forward(req, resp);
        }
    }

    protected void processPostRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = getUsernameFromCookie(req);
        req.setAttribute("username", username);

        String pageName = CommandFactory.getCommand(req).execute(req, resp);
        username = getUsernameFromCookie(req);
        req.setAttribute("username", username);
        if(pageName == null || pageName.isEmpty())
            return;
        else
            req.getRequestDispatcher(pageName).forward(req, resp);

    }

    //Add logic for error processing
    protected String processError(String message) {
        return message;
    }
}
