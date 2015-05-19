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
import java.util.List;

/**
 * Created by Darya on 12.05.2015.
 */
public class RemoveFromOrderCommand implements ServletCommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = (String) request.getAttribute("username");
        int productId = Integer.valueOf(request.getParameter("id"));
        Order order = OrderFactory.getInstance().getOrder(username);

        List<OrderedProduct> products = order.getProducts();
        OrderedProduct product = products.get(products.indexOf(new OrderedProduct().setId(productId)));
        int amount = product.getAmount();
        List<AvailableProduct> productsInDB = DAOFactory.getInstance().getAvailableProductDAO()
                                                .getByBarcode(product.getProduct().getBarcode());
        if(productsInDB.size() > 0) {
            for(AvailableProduct productInDB: productsInDB) {
                if(productInDB.getPrice() == product.getPrice()) {
                    productInDB.setAmount(productInDB.getAmount() + amount);
                    DAOFactory.getInstance().getAvailableProductDAO().update(productInDB, false);
                    break;
                }
            }
        } else {
            DAOFactory.getInstance().getAvailableProductDAO()
                    .save(new AvailableProduct(product.getProduct(), product.getAmount(), product.getPrice()));
        }

        products.remove(new OrderedProduct().setId(productId));
        order = OrderFactory.getInstance().getOrder(username);
        request.setAttribute("order", order);
        return Page.ORDER;
    }
}
