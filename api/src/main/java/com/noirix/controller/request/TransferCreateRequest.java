package com.noirix.controller.request;

import lombok.Data;


@Data
public class TransferCreateRequest {

    private String startPlace;

    private String endPlace;

    private Double startOdometerReading;

    private Double endOdometerReading;

    private Double startAmountOfFuel;

    private Double endAmountOfFuel;
}
