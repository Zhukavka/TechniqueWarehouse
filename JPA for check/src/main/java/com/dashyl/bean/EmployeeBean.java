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
    // ����� ��������������� ����������� Glassfish
    // unitName = "BSUIR" - ��� ��� persistence-unit
    // EntityManager ���� ����������� ��������� CRUD ������� � ��
    @PersistenceContext(unitName = "BSUIRMODE")
    private EntityManager em;

    //��������� Employee � ��
    public Employee add(Employee employee) {
        return em.merge(employee);
    }

    //�������� ��������� �� id
    public Employee get(Long id) {
        return em.find(Employee.class, id);
    }

    // ��������� ���������
    // ���� Employee �������� �� �������� �������� ���,
    // �� ��������� �� ��� �����
    public void update(Employee employee) {
        add(employee);
    }

    //������� ��������� �� id
    public void delete(Long id) {
        em.remove(get(id));
    }

    //�������� ���� ���������� �� ��
    public List<Employee> getAll() {
        TypedQuery<Employee> namedQuery = em.createNamedQuery("Employee.getAll", Employee.class);
        return namedQuery.getResultList();
    }
}
