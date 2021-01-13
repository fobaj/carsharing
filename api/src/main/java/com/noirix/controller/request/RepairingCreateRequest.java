package com.noirix.controller.request;

import lombok.Data;

// A class to use as a request in the Clientcontroller.

@Data
public class RepairingCreateRequest {

    private String damage;

    private Double cost;
}
