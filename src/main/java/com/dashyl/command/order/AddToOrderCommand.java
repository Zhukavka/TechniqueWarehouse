package com.dashyl.command.order;

import com.dashyl.OrderFactory;
import com.dashyl.command.ServletCommand;
import com.dashyl.entity.AvailableProduct;
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
public class AddToOrderCommand implements ServletCommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int productId = Integer.valueOf(request.getParameter("id"));
        try {
            int amount = Integer.valueOf(request.getParameter("amount"));
            if(amount > 0) {
                AvailableProduct product = DAOFactory.getInstance().getAvailableProductDAO().get(productId);
                if(amount <= product.getAmount()) {
                    OrderFactory.getInstance().addProductToOrder((String) request.getAttribute("username"), product, amount);
                    product.setAmount(product.getAmount() - amount);
                    DAOFactory.getInstance().getAvailableProductDAO().update(product, false);
                    request.setAttribute("messageType", "success");
                    request.setAttribute("message", "Товар добавлен к текущему заказу");
                }
            } else {
                request.setAttribute("messageType", "error");
                request.setAttribute("message", "Товар не добавлен. Причина: неверный формат ввода кол-ва");
            }
        } catch(NumberFormatException ex) {
            request.setAttribute("messageType", "error");
            request.setAttribute("message", "Товар не добавлен. Причина: неверный формат ввода кол-ва");
        } catch(Exception ex) {
            ex.printStackTrace();
            request.setAttribute("messageType", "error");
            request.setAttribute("message", "Товар не добавлен. Причина: " + ex.getMessage());
        } finally {
            List<AvailableProduct> products =  DAOFactory.getInstance().getAvailableProductDAO().getAll();
            request.setAttribute("products", products);
            return Page.HOME;
        }
    }
}
