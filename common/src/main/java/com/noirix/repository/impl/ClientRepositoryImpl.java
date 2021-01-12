package com.noirix.repository.impl;

import com.noirix.domain.Client;
import com.noirix.repository.ClientRepository;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Log4j2
public class ClientRepositoryImpl implements ClientRepository {

    private SessionFactory sessionFactory;

    public ClientRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Client> findAll() {
        try(Session session = sessionFactory.openSession()) {
            return session.createQuery("select client from Client client order by client.id asc",
                    Client.class).getResultList();
        }
    }

    @Override
    public Client findById(Long key) {
        try(Session session = sessionFactory.openSession()) {
            return session.find(Client.class, key);
        }
    }


    @Override
    public Client save(Client object) {
        try(Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(object);
            return object;
        }
    }

    @Override
    public Client update(Client object) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(object);
            transaction.commit();
            session.close();
            return object;
        }
    }

    @Override
    public Long delete(Client object) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Long id = object.getId();
            session.delete(object);
            transaction.commit();
            session.close();
            return id;
        }
    }
}
