package com.dashyl.DAO;

import com.dashyl.entity.AvailableProduct;
import com.dashyl.entity.Product;
import com.dashyl.util.DAOUtil;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Darya on 05.04.2015.
 */
public class AvailableProductDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("TechniqueWarehouse").createEntityManager();

    public void save(AvailableProduct product){
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }

    public void delete(AvailableProduct product) {
        em.getTransaction().begin();
        em.remove(product);
        em.getTransaction().commit();
    }

    public AvailableProduct get(int id) {
        return em.find(AvailableProduct.class, id);
    }

    public AvailableProduct getByBarcode(String barcode) {
        return (AvailableProduct) em.createQuery("SELECT c FROM AvailableProduct c WHERE c.product.barcode = :barcode")
                .setParameter("barcode", barcode).getSingleResult();
    }

    public List getByCategory(String category) {
        return em.createQuery("SELECT c FROM AvailableProduct c WHERE c.product.category.name LIKE :category")
                .setParameter("category", category).getResultList();
    }

    public void update(AvailableProduct product) {
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
    }

    public List<AvailableProduct> getAll() {
        TypedQuery<AvailableProduct> namedQuery = em.createNamedQuery("AvailableProduct.getAll", AvailableProduct.class);

        return namedQuery.getResultList();
    }

}
