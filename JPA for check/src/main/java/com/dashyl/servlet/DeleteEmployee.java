package com.dashyl.servlet;

import com.dashyl.bean.EmployeeBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Darya on 27.04.2015.
 */
@WebServlet("/delete")
public class DeleteEmployee extends HttpServlet {

    @EJB
    private EmployeeBean employeeBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if(req.getParameter("id") != null && req.getParameter("id") != ""){
            Long id = Long.valueOf(req.getParameter("id"));
            employeeBean.delete(id);
        }
        resp.sendRedirect("list");
    }
}
