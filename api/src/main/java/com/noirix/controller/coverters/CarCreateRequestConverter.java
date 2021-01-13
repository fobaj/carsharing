package com.noirix.controller.coverters;

import com.noirix.controller.request.CarCreateRequest;
import com.noirix.domain.Car;
import com.noirix.domain.Request;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Collections;

// An additional class for using converters.

@Component
public class CarCreateRequestConverter extends CarConverter<CarCreateRequest, Car> {

    @Override
    public Car convert(CarCreateRequest carCreateRequest) {

        Car car = new Car();

        Request request = new Request();
        request.setStartDateAndTime(new Timestamp(System.currentTimeMillis()));
        request.setEndDateAndTime(new Timestamp(System.currentTimeMillis()));
        car.setRequests(Collections.singleton(new Request(car, request.getStartDateAndTime(),
                request.getEndDateAndTime())));

        return doConvert(car, carCreateRequest);
    }
}