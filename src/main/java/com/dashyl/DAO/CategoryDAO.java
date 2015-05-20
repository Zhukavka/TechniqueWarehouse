package com.dashyl.DAO;

import com.dashyl.entity.Category;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Darya on 05.04.2015.
 */
public class CategoryDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("TechniqueWarehouse").createEntityManager();

    public void save(Category category){
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
    }

    public Category get(int id) {
        Category category = em.find(Category.class, id);
        return category;
    }

    public List<Category> getAll() {
        TypedQuery<Category> namedQuery = em.createNamedQuery("Category.getAll", Category.class);
        List<Category> categories = namedQuery.getResultList();
        return categories;
    }
}
