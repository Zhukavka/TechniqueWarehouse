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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Darya on 17.05.2015.
 */
public class OrdersBetweenDates implements ServletCommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Object dateFrom = request.getParameter("fromDate");
        Object dateTo = request.getParameter("toDate");

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            Date from = format.parse(dateFrom.toString());
            Date to = format.parse(dateTo.toString());
            List<Order> orders = DAOFactory.getInstance().getOrderDAO().getOrdersBetweenDates(from, to);
            if(orders == null || orders.isEmpty())
                orders = DAOFactory.getInstance().getOrderDAO().getAll();
            request.setAttribute("orders", orders);
            request.setAttribute("messageType", "info");
            request.setAttribute("message", "Сортировка по периоду с " + format.format(from) + " до " + format.format(to) );
        } catch(Exception ex) {
            ex.printStackTrace();
            request.setAttribute("messageType", "error");
            request.setAttribute("message", "Ошибка: " + ex.getMessage() );
        } finally {
            List<Client> clients = DAOFactory.getInstance().getClientDAO().getAll();
            request.setAttribute("clients", clients);

            return Page.ORDERS;
        }
    }
}

