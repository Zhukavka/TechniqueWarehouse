package com.dashyl.command.order;

import com.dashyl.command.ServletCommand;
import com.dashyl.entity.AvailableProduct;
import com.dashyl.entity.Order;
import com.dashyl.service.CreateReport;
import com.dashyl.servlet.manager.Page;
import com.dashyl.util.DAOFactory;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Darya on 17.05.2015.
 */
public class SaveReportCommand implements ServletCommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int orderId = Integer.valueOf(request.getParameter("id"));
        Order order = DAOFactory.getInstance().getOrderDAO().get(orderId);
        try {
            CreateReport.generateReport(order);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        List<AvailableProduct> products =  DAOFactory.getInstance().getAvailableProductDAO().getAll();
        request.setAttribute("products", products);
        return Page.HOME;
    }
}
