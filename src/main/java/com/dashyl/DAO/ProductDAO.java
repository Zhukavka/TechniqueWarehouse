package com.dashyl.DAO;


import com.dashyl.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Darya on 05.04.2015.
 */
public class ProductDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("TechniqueWarehouse").createEntityManager();

    public void save(Product product){
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }

    public void delete(Product product) {
        em.getTransaction().begin();
        em.remove(product);
        em.getTransaction().commit();
    }

    public Product get(int id) {
        Product product = em.find(Product.class, id);
        return product;
    }

    public Product getByBarcode(String barcode) {
        Product product = em.createNamedQuery("Product.getByBarcode", Product.class)
                .setParameter("barcode", barcode).getSingleResult();
        return product;
    }

    public void update(Product product) {
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
    }

    public List<Product> getAll() {
        TypedQuery<Product> namedQuery = em.createNamedQuery("Product.getAll", Product.class);
        List<Product> products = namedQuery.getResultList();
        return products;
    }

}
