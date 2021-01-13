package com.noirix.controller.coverters;

import com.noirix.controller.request.CarCreateRequest;

import com.noirix.domain.Car;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;

// Abstract class for using converters.

public abstract class CarConverter<S, T> implements Converter<S, T> {

    protected Car doConvert(Car car, CarCreateRequest carCreateRequest) {

        car.setBrand(carCreateRequest.getBrand());
        car.setModel(carCreateRequest.getModel());
        car.setYearOfIssue(carCreateRequest.getYearOfIssue());
        car.setColor(carCreateRequest.getColor());
        car.setVin(carCreateRequest.getVin());
        car.setRegistrationPlate(carCreateRequest.getRegistrationPlate());
        car.setNumberOfSeats(carCreateRequest.getNumberOfSeats());
        car.setRate(carCreateRequest.getRate());
        car.setCreated(new Timestamp(System.currentTimeMillis()));
        car.setChanged(new Timestamp(System.currentTimeMillis()));
        car.setIsDeleted(carCreateRequest.getIsDeleted());

        return car;
    }
}