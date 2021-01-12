package com.noirix.controller.request;

import com.noirix.domain.Request;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@Data
public class CarCreateRequest {

    private String brand;

    private String model;

    private Integer yearOfIssue;

    private String color;

    private String vin;

    private String registrationPlate;

    private Integer numberOfSeats;

    private Double rate;

    private Timestamp created;

    private Timestamp changed;

    private Boolean isDeleted = false;

    private Set<Request> requests = Collections.emptySet();

}
