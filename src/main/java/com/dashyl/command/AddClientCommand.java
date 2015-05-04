package com.dashyl.command;

import com.dashyl.entity.Client;
import com.dashyl.util.DAOUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Darya on 04.05.2015.
 */
public class AddClientCommand implements Command {

    private HttpServletRequest request;

    public AddClientCommand(HttpServletRequest req) {
        this.request = req;
    }

    public void execute() {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        /*Потом написать проверки правильности ввода*/

        DAOUtil.getInstance().getClientDAO().save(new Client(name, email, phone));
    }
}


