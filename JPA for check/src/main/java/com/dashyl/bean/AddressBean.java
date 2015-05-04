package com.dashyl.bean;

import com.dashyl.entity.Address;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Darya on 27.04.2015.
 */
@Stateless
public class AddressBean {
    // ����� ��������������� ����������� Glassfish
    // unitName = "BSUIR" - ��� ��� persistence-unit
    // EntityManager ���� ����������� ��������� CRUD ������� � ��z

    //private EntityManagerFactory emf;
    @PersistenceContext(unitName = "BSUIRMODE")
    private EntityManager em;

    //��������� Address � ��
    public Address add(Address address) {
        return em.merge(address);
    }

    //�������� ����� �� id
    public Address get(Long id) {
        return em.find(Address.class, id);
    }

    // ��������� �����
    // ���� Address �������� �� �������� �������� ���,
    // �� ��������� �� ��� �����
    public void update(Address address) {
        add(address);
    }

    //������� ����� �� id
    public void delete(Long id) {
        em.remove(get(id));
    }

    //�������� ��� ������ �� ��
   public List<Address> getAll() {
       /*emf = Persistence.createEntityManagerFactory("BSUIRMODE");
       em = emf.createEntityManager();
       CriteriaBuilder builder = em.getCriteriaBuilder();
       CriteriaQuery<Address> criteria = builder.createQuery(Address.class );
       Root<Address> personRoot = criteria.from( Address.class );
       criteria.select(personRoot);
       List<Address>usrList=em.createQuery(String.valueOf(criteria)).getResultList();
       em.close();
       return usrList;*/

        TypedQuery<Address> namedQuery = em.createNamedQuery("Address.getAll", Address.class);
        return namedQuery.getResultList();
    }
}
