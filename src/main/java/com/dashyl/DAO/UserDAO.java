package com.dashyl.DAO;

import com.dashyl.entity.Order;
import com.dashyl.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Darya on 21.04.2015.
 */
public class UserDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("TechniqueWarehouse").createEntityManager();

    public void save(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public List<User> getAll() {
        TypedQuery<User> namedQuery = em.createNamedQuery("User.getAll", User.class);

        return namedQuery.getResultList();
    }
}
