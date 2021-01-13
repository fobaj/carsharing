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

    // Autowiring bean.

    private SessionFactory sessionFactory;

    public CarRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Method for finding all cars.

    @Override
    public List<Car> findAll() {
        try(Session session = sessionFactory.openSession()) {
            return session.createQuery("select car from Car car order by car.id asc",
                    Car.class).getResultList();
        }
    }

    // Method for finding car by id.

    @Override
    public Car findById(Long key) {
        try(Session session = sessionFactory.openSession()) {
            return session.find(Car.class, key);
        }
    }

    // Method for creating a car.

    @Override
    public Car save(Car object) {
        try(Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(object);
            return object;
        }
    }

    // Method for updating a car.

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

    // Method for deleting a car.

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
