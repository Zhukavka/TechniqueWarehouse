package com.dashyl.bean;

import com.dashyl.entity.Employee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Darya on 27.04.2015.
 */
@Stateless
public class EmployeeBean {
    // Будет инициализирован контейнером Glassfish
    // unitName = "BSUIR" - это имя persistence-unit
    // EntityManager дает возможность выполнять CRUD запросы в БД
    @PersistenceContext(unitName = "BSUIRMODE")
    private EntityManager em;

    //Добавляем Employee в БД
    public Employee add(Employee employee) {
        return em.merge(employee);
    }

    //Получаем работника по id
    public Employee get(Long id) {
        return em.find(Employee.class, id);
    }

    // обновляем работника
    // если Employee которого мы пытаемся обновить нет,
    // то запишется он как новый
    public void update(Employee employee) {
        add(employee);
    }

    //Удаляем работника по id
    public void delete(Long id) {
        em.remove(get(id));
    }

    //Получаем всех работников из БД
    public List<Employee> getAll() {
        TypedQuery<Employee> namedQuery = em.createNamedQuery("Employee.getAll", Employee.class);
        return namedQuery.getResultList();
    }
}
