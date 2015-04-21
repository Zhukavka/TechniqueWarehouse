package com.dashyl.DAO;

import com.dashyl.entity.Client;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Darya on 05.04.2015.
 */
public class ClientDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("DBUnitEx").createEntityManager();

    public void save(Client client){
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
    }

    public void delete(Client client) {
        em.getTransaction().begin();
        em.remove(client);
        em.getTransaction().commit();
    }

    public Client get(int id) {
        return em.find(Client.class, id);
    }

    public void update(Client client) {
        em.getTransaction().begin();
        em.merge(client);
        em.getTransaction().commit();
    }

    public List<Client> getAll() {
        TypedQuery<Client> namedQuery = em.createNamedQuery("Client.getAll", Client.class);

        return namedQuery.getResultList();
    }

}
