package com.dashyl.command.product;

import com.dashyl.command.ServletCommand;
import com.dashyl.entity.AvailableProduct;
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
public class FindProductCommand implements ServletCommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String criteria = request.getParameter("criteria");
        String findValue = request.getParameter("findValue");
        List<AvailableProduct> products = new ArrayList<AvailableProduct>();
        if(!findValue.isEmpty()) {
            if (criteria.equals("barcode")) {
                AvailableProduct product = DAOFactory.getInstance().getAvailableProductDAO().getByBarcode(findValue);
                products.add(product);
            } else if (criteria.equals("category")) {
                products = DAOFactory.getInstance().getAvailableProductDAO().getByCategory(findValue);
            }
        } else
            products = DAOFactory.getInstance().getAvailableProductDAO().getAll();
        request.setAttribute("products", products);
        return Page.HOME;
    }
}
