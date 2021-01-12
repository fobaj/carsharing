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

    private SessionFactory sessionFactory;

    public RentingRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Renting> findAll() {
        try(Session session = sessionFactory.openSession()) {
            return session.createQuery("select renting from Renting renting order by renting.id asc",
                    Renting.class).getResultList();
        }
    }

    @Override
    public Renting findById(Long key) {
        try(Session session = sessionFactory.openSession()) {
            return session.find(Renting.class, key);
        }
    }

    @Override
    public Renting save(Renting object) {
        try(Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(object);
            return object;
        }
    }

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
