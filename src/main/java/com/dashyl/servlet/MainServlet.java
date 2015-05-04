package com.dashyl.servlet;

import com.dashyl.DAO.UserDAO;
import com.dashyl.command.AddClientCommand;
import com.dashyl.command.AddProductsCommand;
import com.dashyl.entity.AvailableProduct;
import com.dashyl.entity.Client;
import com.dashyl.entity.User;
import com.dashyl.util.DAOUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
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

    protected void processGetRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = null;
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("user")) username = cookie.getValue();
            }
        }
        req.setAttribute("username", username);
        String event = req.getParameter("event");
        if(event == null) {
            List<AvailableProduct> products =  DAOUtil.getInstance().getAvailableProductDAO().getAll();
            req.setAttribute("products", products);
            req.getRequestDispatcher("static/jsp/mainpage.jsp").forward(req, resp);
        } else if(event.equals("auth")) {
            String message = req.getParameter("message");
            if(message != null) {
                req.setAttribute("message", processError(message));
            }
            req.getRequestDispatcher("static/jsp/auth.jsp").forward(req, resp);
        } else if(event.equals("all_orders")) {
            req.getRequestDispatcher("static/jsp/all_orders.jsp").forward(req, resp);
        } else if(event.equals("choose_client")) {
            req.getRequestDispatcher("static/jsp/choose_client.jsp").forward(req, resp);
        } else if(event.equals("clients")) {
            List<Client> clients = DAOUtil.getInstance().getClientDAO().getAll();
            req.setAttribute("clients", clients);
            req.getRequestDispatcher("static/jsp/clients.jsp").forward(req, resp);
        } else if(event.equals("new_client")){
            req.getRequestDispatcher("static/jsp/new_client.jsp").forward(req, resp);
        } else if(event.equals("new_user")) {
            req.getRequestDispatcher("static/jsp/new_user.jsp").forward(req, resp);
        } else if(event.equals("order")) {
            req.getRequestDispatcher("static/jsp/order.jsp").forward(req, resp);
        } else if(event.equals("add_products")){
            req.getRequestDispatcher("static/jsp/add_products.jsp").forward(req, resp);
        } else{
            List<AvailableProduct> products =  DAOUtil.getInstance().getAvailableProductDAO().getAll();
            req.setAttribute("products", products);
            req.getRequestDispatcher("static/jsp/mainpage.jsp").forward(req, resp);
        }


    }

    protected void processPostRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = null;
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("user")) username = cookie.getValue();
            }
        }
        req.setAttribute("username", username);

        String event = req.getParameter("event");
        if(event.equals("login")) {
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
                        resp.sendRedirect("warehouse?event=available_prod");
                        break;
                    }
                }
            } else if(key.equals("admin") && user.equals("admin")) {
                Cookie loginCookie = new Cookie("user", user);
                //setting cookie to expiry in 12 hours
                loginCookie.setMaxAge(60 * 60 * 12);
                resp.addCookie(loginCookie);
                resp.sendRedirect("warehouse?event=available_prod");
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/warehouse");
                PrintWriter out = resp.getWriter();
                out.println("<font color=red>Either user name or password is wrong.</font>");
                rd.include(req, resp);
            }
        } else if(event.equals("logout")) {
            resp.setContentType("text/html");
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
                resp.addCookie(loginCookie);
            }
            List<AvailableProduct> products =  DAOUtil.getInstance().getAvailableProductDAO().getAll();
            req.setAttribute("products", products);
            req.getRequestDispatcher("static/jsp/mainpage.jsp").forward(req, resp);
        } else if(event.equals("new_user")) {
            String user = req.getParameter("user");
            UserDAO userService = new UserDAO();
            userService.save(new User(user));
            resp.sendRedirect("warehouse");
        } else if (event.equals("add_products")) {
            final Part filePart = req.getPart("file");
            final String fileName = getFileName(filePart);

            InputStream fileContent = null;
            final PrintWriter writer = resp.getWriter();
            try {
                fileContent = filePart.getInputStream();
                filePart.write("H:" + File.separator + fileName);
                FileReader reader = new FileReader("H:" + File.separator + fileName);
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

                new AddProductsCommand(jsonObject).execute();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                if (fileContent != null) {
                    fileContent.close();
                }
                if (writer != null) {
                    writer.close();
                }
            }
        } else if(event.equals("new_client")) {
            new AddClientCommand(req).execute();
            List<AvailableProduct> products =  DAOUtil.getInstance().getAvailableProductDAO().getAll();
            req.setAttribute("products", products);


            req.setAttribute("username", username);
            req.getRequestDispatcher("static/jsp/mainpage.jsp").forward(req, resp);
        } else if(event.equals("findProduct")) {
            String criteria = req.getParameter("criteria");
            String findValue = req.getParameter("findValue");
            if(criteria.equals("barcode")) {
                AvailableProduct product = DAOUtil.getInstance().getAvailableProductDAO().getByBarcode(findValue);
                List<AvailableProduct> products = new ArrayList<AvailableProduct>();
                products.add(product);
                req.setAttribute("products", products);
                req.getRequestDispatcher("static/jsp/mainpage.jsp").forward(req, resp);
            } else if(criteria.equals("name")) {
                List products = DAOUtil.getInstance().getAvailableProductDAO().getByCategory(findValue);
                req.setAttribute("products", products);
                req.getRequestDispatcher("static/jsp/mainpage.jsp").forward(req, resp);
            }
        }
    }

    //Add logic for error processing
    protected String processError(String message) {
        return message;
    }

    protected String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");

        for(String content: part.getHeader("content-disposition").split(";")) {
            if(content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
