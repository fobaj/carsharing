package com.noirix.repository.impl;

import com.noirix.domain.Car;
import com.noirix.repository.CarRepository;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Log4j2
public class CarRepositoryImpl implements CarRepository {

    private SessionFactory sessionFactory;

    public CarRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Car> findAll() {
        try(Session session = sessionFactory.openSession()) {
            return session.createQuery("select car from Car car order by car.id asc",
                    Car.class).getResultList();
        }
    }

    @Override
    public Car findById(Long key) {
        try(Session session = sessionFactory.openSession()) {
            return session.find(Car.class, key);
        }
    }

    @Override
    public Car save(Car object) {
        try(Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(object);
            return object;
        }
    }

    @Override
    public Car update(Car object) {
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
    public Long delete(Car object) {
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
