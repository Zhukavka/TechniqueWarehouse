package com.dashyl.DAO;

import com.dashyl.entity.OrderedProduct;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Darya on 05.04.2015.
 */
public class OrderedProductDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("DBUnitEx").createEntityManager();

    public void save(OrderedProduct product){
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }

    public void delete(OrderedProduct product) {
        em.getTransaction().begin();
        em.remove(product);
        em.getTransaction().commit();
    }

    public OrderedProduct get(int id) {
        return em.find(OrderedProduct.class, id);
    }

    public void update(OrderedProduct product) {
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
    }

    public List<OrderedProduct> getAll() {
        TypedQuery<OrderedProduct> namedQuery = em.createNamedQuery("OrderedProduct.getAll", OrderedProduct.class);

        return namedQuery.getResultList();
    }

}