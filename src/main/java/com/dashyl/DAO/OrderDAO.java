package com.dashyl.DAO;

import com.dashyl.entity.Client;
import com.dashyl.entity.Order;
import com.dashyl.entity.OrderedProduct;
import com.dashyl.util.DAOFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * Created by Darya on 05.04.2015.
 */
public class OrderDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("TechniqueWarehouse").createEntityManager();

    public void save(Order order){
        for(OrderedProduct product: order.getProducts()) {
            DAOFactory.getInstance().getOrderedProductDAO().save(product);
        }
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
        Order order = em.find(Order.class, id);
        return order;
    }

    public List<Order> getOrdersBetweenDates(Date from, Date to) {
        List<Order> orders = (List<Order>) em.createQuery("SELECT o FROM Order o WHERE o.date >= :fromDate AND o.date <= :toDate")
                .setParameter("fromDate", from).setParameter("toDate", to).getResultList();
        return orders;
    }

    public List<Order> getOrdersByClient(Client client) {
        List<Order> orders = (List<Order>) em.createQuery("SELECT o FROM Order o WHERE o.client.id = :client_id")
                .setParameter("client_id", client.getId()).getResultList();
        return orders;
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
