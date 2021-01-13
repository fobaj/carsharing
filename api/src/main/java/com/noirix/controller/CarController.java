package com.noirix.controller;

import com.noirix.controller.request.*;
import com.noirix.domain.Car;
import com.noirix.repository.CarRepository;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/rest/cars")
@RequiredArgsConstructor
public class CarController {

        public final CarRepository carRepository;

        public final ConversionService conversionService;


        @ApiOperation(value = "Finding all cars")
        @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading cars"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
        })
        @GetMapping
        public ResponseEntity<List<Car>> findAllCars() {
            return new ResponseEntity<>(carRepository.findAll(), HttpStatus.OK);
        }


        @ApiOperation(value = "Finding car by id")
        @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading car"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
        })
        @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Car database id", example = "7", required = true, dataType = "long"
                    , paramType = "path")
        })
        @GetMapping("/{id}")
        @ResponseStatus(HttpStatus.OK)
        public Car findCarById(@PathVariable Long id) {
            return carRepository.findById(id);
        }


        @ApiOperation(value = "Create car")
        @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation car"),
            @ApiResponse(code = 422, message = "Failed car creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
        })
        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Car savingCar(@RequestBody CarCreateRequest carCreateRequest) {

            Car car = conversionService.convert(carCreateRequest, Car.class);

            return carRepository.save(car);
        }


        @ApiOperation(value = "Update car")
        @ApiResponses({
            @ApiResponse(code = 201, message = "Successful updating car"),
            @ApiResponse(code = 422, message = "Failed car updating properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
        })
        @PutMapping("/{id}")
        @ResponseStatus(HttpStatus.OK)
        public Car updateCar(@PathVariable Long id, @RequestBody CarCreateRequest carCreateRequest) {

            Car car = carRepository.findById(id);

            car.setBrand(carCreateRequest.getBrand());
            car.setModel(carCreateRequest.getModel());
            car.setYearOfIssue(carCreateRequest.getYearOfIssue());
            car.setColor(carCreateRequest.getColor());
            car.setVin(carCreateRequest.getVin());
            car.setRegistrationPlate(carCreateRequest.getRegistrationPlate());
            car.setNumberOfSeats(carCreateRequest.getNumberOfSeats());
            car.setRate(carCreateRequest.getRate());
            car.setChanged(new Timestamp(System.currentTimeMillis()));
            car.setIsDeleted(carCreateRequest.getIsDeleted());
            car.setRequests(car.getRequests());

            return carRepository.update(car);
        }


        @ApiOperation(value = "Update2 car")
        @ApiResponses({
            @ApiResponse(code = 201, message = "Successful updating car"),
            @ApiResponse(code = 422, message = "Failed car updating properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
        })
        @PutMapping
        @ResponseStatus(HttpStatus.OK)
        public Car update2Car(@RequestBody CarChangeRequest carChangeRequest) {

            Car car = carRepository.findById(carChangeRequest.getId());

            car.setBrand(carChangeRequest.getBrand());
            car.setModel(carChangeRequest.getModel());
            car.setYearOfIssue(carChangeRequest.getYearOfIssue());
            car.setColor(carChangeRequest.getColor());
            car.setVin(carChangeRequest.getVin());
            car.setRegistrationPlate(carChangeRequest.getRegistrationPlate());
            car.setNumberOfSeats(carChangeRequest.getNumberOfSeats());
            car.setRate(carChangeRequest.getRate());
            car.setChanged(new Timestamp(System.currentTimeMillis()));
            car.setIsDeleted(carChangeRequest.getIsDeleted());
            car.setRequests(car.getRequests());

            return carRepository.update(car);
        }


        @ApiOperation(value = "Deleting car by id")
        @ApiResponses({
            @ApiResponse(code = 200, message = "Successful deleting car"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
        })
        @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Car database id", example = "7", required = true, dataType =
                    "long"
                    , paramType = "path")
        })
        @DeleteMapping("/delete")
        @ResponseStatus(HttpStatus.OK)
        public Long deleteCar(@PathVariable Long id) {

            Car car = carRepository.findById(id);

            return carRepository.delete(car);

        }
}