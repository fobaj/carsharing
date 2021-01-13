package com.noirix.repository.impl;

import com.noirix.domain.Renting;
import com.noirix.repository.RentingRepository;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Log4j2
public class RentingRepositoryImpl implements RentingRepository {

    // Autowiring bean.

    private SessionFactory sessionFactory;

    public RentingRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    // Method for finding all rentings.

    @Override
    public List<Renting> findAll() {
        try(Session session = sessionFactory.openSession()) {
            return session.createQuery("select renting from Renting renting order by renting.id asc",
                    Renting.class).getResultList();
        }
    }

    // Method for finding renting by id.

    @Override
    public Renting findById(Long key) {
        try(Session session = sessionFactory.openSession()) {
            return session.find(Renting.class, key);
        }
    }

    // Method for creating a renting.

    @Override
    public Renting save(Renting object) {
        try(Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(object);
            return object;
        }
    }

    // Method for updating a renting.

    @Override
    public Renting update(Renting object) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(object);
            transaction.commit();
            session.close();
            return object;
        }
    }

    // Method for deleting a renting.

    @Override
    public Long delete(Renting object) {
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
