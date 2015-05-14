package com.dashyl.command;

import com.dashyl.DAO.UserDAO;
import com.dashyl.entity.User;
import com.dashyl.servlet.manager.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Darya on 12.05.2015.
 */
public class AddUserCommand implements ServletCommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String user = request.getParameter("user");
        UserDAO userService = new UserDAO();
        userService.save(new User(user));
        return Page.HOME;
    }
}
