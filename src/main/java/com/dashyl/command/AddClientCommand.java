package com.dashyl.command;

import com.dashyl.entity.Client;
import com.dashyl.servlet.manager.Page;
import com.dashyl.util.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Darya on 04.05.2015.
 */
public class AddClientCommand implements ServletCommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        /*Потом написать проверки правильности ввода*/

        DAOFactory.getInstance().getClientDAO().save(new Client(name, email, phone));

        return Page.HOME;
    }
}


