package com.dashyl.DAO;

import com.dashyl.entity.AvailableProduct;

import javax.persistence.Query;
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
        em.remove(em.contains(product) ? product : em.merge(product));
        em.getTransaction().commit();
    }

    public AvailableProduct get(int id) {
        return em.find(AvailableProduct.class, id);
    }

    public List getSortedByName(boolean order) {
        String sortOrder = order ? "ASC" : "DESC";
        Query query = em.createQuery("SELECT c FROM AvailableProduct c ORDER BY c.product.name " + sortOrder);
        return query.getResultList();
    }
    public List getSortedByPrice(boolean order) {
        String sortOrder = order ? "ASC" : "DESC";
        Query query = em.createQuery("SELECT c FROM AvailableProduct c ORDER BY c.price " + sortOrder);
        return query.getResultList();
    }

    public List<AvailableProduct> getByBarcode(String barcode) {
        String temp = "%" + barcode + "%";
        Query query = em.createQuery("SELECT c FROM AvailableProduct c WHERE c.product.barcode LIKE :barcode")
                .setParameter("barcode", temp);
        return query.getResultList();
    }

    public List getByCategory(String category) {
        String temp = "%" + category + "%";
        return em.createQuery("SELECT c FROM AvailableProduct c WHERE c.product.category.name LIKE :category")
                .setParameter("category", temp).getResultList();
    }

    public void update(AvailableProduct product, boolean loadFromFile) {
        if(product.getAmount() == 0) {
            delete(product);
            return;
        }

        List<AvailableProduct> products = this.getByBarcode(product.getProduct().getBarcode());
        if(products.size() > 0 && loadFromFile) {
            for(AvailableProduct productInDb: products) {
                if(productInDb.getPrice() == product.getPrice()){
                    productInDb.setAmount(productInDb.getAmount() + product.getAmount());
                    em.getTransaction().begin();
                    em.merge(productInDb);
                    em.getTransaction().commit();
                    return;
                }
            }
            this.save(product);
        } else {
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
        }
    }

    public List<AvailableProduct> getAll() {
        TypedQuery<AvailableProduct> namedQuery = em.createNamedQuery("AvailableProduct.getAll", AvailableProduct.class);
        List<AvailableProduct> products = namedQuery.getResultList();
        for(AvailableProduct product: products) {
            if(product.getAmount() == 0){
                this.delete(product);
            }
        }
        return products;
    }

}
