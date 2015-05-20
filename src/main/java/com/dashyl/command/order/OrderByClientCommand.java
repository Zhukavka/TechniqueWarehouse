package com.dashyl.command.order;

import com.dashyl.command.ServletCommand;
import com.dashyl.entity.Client;
import com.dashyl.entity.Order;
import com.dashyl.servlet.manager.Page;
import com.dashyl.util.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Darya on 17.05.2015.
 */
public class OrderByClientCommand implements ServletCommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int clientId = Integer.valueOf(request.getParameter("criteria"));

        Client client = DAOFactory.getInstance().getClientDAO().get(clientId);
        List<Order> orders;
        if(client != null) {
            orders =  DAOFactory.getInstance().getOrderDAO().getOrdersByClient(client);

        } else
            orders = DAOFactory.getInstance().getOrderDAO().getAll();
        request.setAttribute("orders", orders);
        List<Client> clients = DAOFactory.getInstance().getClientDAO().getAll();
        request.setAttribute("clients", clients);
        request.setAttribute("messageType", "info");
        request.setAttribute("message", "Сортировка по клиенту " + client.getName() );
        return Page.ORDERS;
    }
}
