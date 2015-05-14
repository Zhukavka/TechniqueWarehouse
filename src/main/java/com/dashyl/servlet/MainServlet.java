package com.dashyl.servlet;

import com.dashyl.OrderFactory;
import com.dashyl.command.manager.CommandFactory;
import com.dashyl.entity.*;
import com.dashyl.servlet.manager.Page;
import com.dashyl.util.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;

/**
 * Created by Darya on 17.04.2015.
 **/


@MultipartConfig(location = "H:\\")//это потом в конфиг вынести
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
        if(event == null){
            List<AvailableProduct> products =  DAOFactory.getInstance().getAvailableProductDAO().getAll();
            req.setAttribute("products", products);
            req.getRequestDispatcher(Page.HOME).forward(req, resp);
        } else if(event.equals("auth")) {
            String message = req.getParameter("message");
            if(message != null) {
                req.setAttribute("message", processError(message));
            }
            req.getRequestDispatcher(Page.AUTH).forward(req, resp);
        } else if(event.equals("all_orders")) {
            req.getRequestDispatcher(Page.ORDERS).forward(req, resp);
        } else if(event.equals("choose_client")) {
            req.getRequestDispatcher("static/jsp/choose_client.jsp").forward(req, resp);
        } else if(event.equals("clients")) {
            List<Client> clients = DAOFactory.getInstance().getClientDAO().getAll();
            req.setAttribute("clients", clients);
            req.getRequestDispatcher("static/jsp/clients.jsp").forward(req, resp);
        } else if(event.equals("new_client")){
            req.getRequestDispatcher("static/jsp/new_client.jsp").forward(req, resp);
        } else if(event.equals("new_user")) {
            req.getRequestDispatcher("static/jsp/new_user.jsp").forward(req, resp);
        } else if(event.equals("order")) {
            Order order = OrderFactory.getInstance().getOrder(username);
            req.setAttribute("order", order);
            List<Client> clients = DAOFactory.getInstance().getClientDAO().getAll();
            req.setAttribute("clients", clients);
            req.getRequestDispatcher("static/jsp/order.jsp").forward(req, resp);
        } else if(event.equals("add_products")){
            req.getRequestDispatcher("static/jsp/add_products.jsp").forward(req, resp);
        } else{
            List<AvailableProduct> products =  DAOFactory.getInstance().getAvailableProductDAO().getAll();
            req.setAttribute("products", products);
            req.getRequestDispatcher(Page.HOME).forward(req, resp);
        }


    }

    protected void processPostRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = getUsernameFromCookie(req);
        req.setAttribute("username", username);

        String pageName = CommandFactory.getCommand(req).execute(req, resp);
        req.getRequestDispatcher(pageName).forward(req, resp);

    }

    //Add logic for error processing
    protected String processError(String message) {
        return message;
    }
}
