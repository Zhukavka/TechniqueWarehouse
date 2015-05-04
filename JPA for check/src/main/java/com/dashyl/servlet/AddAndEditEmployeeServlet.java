package com.dashyl.servlet;

import com.dashyl.bean.EmployeeBean;
import com.dashyl.entity.Address;
import com.dashyl.entity.Employee;

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
@WebServlet("/add")
public class AddAndEditEmployeeServlet extends HttpServlet {

    @EJB
    private EmployeeBean employeeBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");

        // если параметр null, то пользователь
        // пришел на страницу, чтобы создать нового, иначе
        // будет выполнятся редактирование существующего работника
        if(req.getParameter("edit") != null){
            Long id = Long.valueOf(req.getParameter("edit"));
            Employee employee = employeeBean.get(id);

            req.setAttribute("employee", employee);
        }

        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        float salary = Float.valueOf(req.getParameter("salary"));
        String city = req.getParameter("city");
        String phone = req.getParameter("phone");

        // если id есть, то выполняется редактирование
        // а если нет id, то - это значит, что создается новый работник
        if(!req.getParameter("id").equals("")){
            Long id = Long.valueOf(req.getParameter("id"));
            Employee employee = employeeBean.get(id);
            employee.setSurname(surname);
            employee.setSalary(salary);
            employee.setName(name);

            // обновляем работника
            employeeBean.update(employee);
        } else{
            // добавляем нового
            employeeBean.add(new Employee(name, surname, salary, new Address(city, phone)));
        }

        // перенаправляем на сервлет, который выводит всех работников
        resp.sendRedirect("list");
    }
}
