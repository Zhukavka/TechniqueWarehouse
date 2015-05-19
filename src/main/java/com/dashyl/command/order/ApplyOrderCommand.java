package com.dashyl.command.order;

import com.dashyl.OrderFactory;
import com.dashyl.command.ServletCommand;
import com.dashyl.entity.*;
import com.dashyl.servlet.manager.Page;
import com.dashyl.util.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darya on 12.05.2015.
 */
public class ApplyOrderCommand implements ServletCommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int clientId = Integer.valueOf(request.getParameter("criteria"));

        Client client = DAOFactory.getInstance().getClientDAO().get(clientId);
        Order order = OrderFactory.getInstance().getOrder((String) request.getAttribute("username"));
        if(order != null) {
            List<OrderedProduct> orderedProducts =  new ArrayList<OrderedProduct>();
            for(OrderedProduct product: order.getProducts()) {
                orderedProducts.add(new OrderedProduct(product));
            }
            order.getProducts();

            double cost = 0;
            for(OrderedProduct product: orderedProducts) {
                cost += product.getAmount() * product.getPrice();
            }
            User user = DAOFactory.getInstance().getUserDAO().getByName((String) request.getAttribute("username"));
            DAOFactory.getInstance().getOrderDAO().save(new Order(cost, client, user, orderedProducts));
            OrderFactory.getInstance().deleteAlProducts((String) request.getAttribute("username"));
        }
        List<AvailableProduct> products =  DAOFactory.getInstance().getAvailableProductDAO().getAll();
        request.setAttribute("products", products);
        return Page.HOME;
    }
}
