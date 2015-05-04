package com.dashyl.servlet;

import com.dashyl.bean.EmployeeBean;
import com.dashyl.entity.Employee;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darya on 27.04.2015.
 */
@WebServlet("/list")
public class MainServlet extends HttpServlet {
    // ��������� ������� � ���, ��� ������ ������ ����� ���������������
    // ����������� Glassfish DI
    @EJB
    private EmployeeBean employeeBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //�������� ������ ����������
       List<Employee> allEmployee /*= employeeBean.getAll()*/ = new ArrayList<Employee>();
        allEmployee.add(employeeBean.get(1L));

        //��������� ���������� ������ � request, ������� ���������� �� JSP
        req.setAttribute("employees", allEmployee);

        //���������� request �� JSP
        req.getRequestDispatcher("/list.jsp").forward(req,resp);
    }

}
