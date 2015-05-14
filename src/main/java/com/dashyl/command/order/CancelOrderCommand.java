package com.dashyl.command.order;

import com.dashyl.OrderFactory;
import com.dashyl.command.ServletCommand;
import com.dashyl.entity.AvailableProduct;
import com.dashyl.entity.Order;
import com.dashyl.entity.OrderedProduct;
import com.dashyl.servlet.manager.Page;
import com.dashyl.util.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Darya on 12.05.2015.
 */
public class CancelOrderCommand implements ServletCommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = (String) request.getAttribute("username");
        Order order = OrderFactory.getInstance().getOrder( username );
        for(OrderedProduct product: order.getProducts()) {
            AvailableProduct availProduct = DAOFactory.getInstance().getAvailableProductDAO().getByBarcode(product.getProduct().getBarcode());
            availProduct.setAmount(product.getAmount() + availProduct.getAmount());
            DAOFactory.getInstance().getAvailableProductDAO().update(availProduct);

        }
        OrderFactory.getInstance().deleteAlProducts(username);
        order = OrderFactory.getInstance().getOrder(username);
        request.setAttribute("order", order);
        return Page.HOME;
    }
}
