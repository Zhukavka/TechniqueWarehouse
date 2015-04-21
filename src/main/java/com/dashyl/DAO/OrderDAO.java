package com.dashyl.DAO;

import com.dashyl.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Darya on 05.04.2015.
 */
public class OrderDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("TechniqueWarehouse").createEntityManager();

    public void save(Order order){
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
    }

    public void delete(Order order) {
        em.getTransaction().begin();
        em.remove(order);
        em.getTransaction().commit();
    }

    public Order get(int id) {
        return em.find(Order.class, id);
    }

    public void update(Order order) {
        em.getTransaction().begin();
        em.merge(order);
        em.getTransaction().commit();
    }

    public List<Order> getAll() {
        TypedQuery<Order> namedQuery = em.createNamedQuery("Order.getAll", Order.class);

        return namedQuery.getResultList();
    }

}
