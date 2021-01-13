package com.noirix.controller.request;

import lombok.Data;

// A class to use as a request in the Rentingcontroller.

@Data
public class RentingChangeRequest extends RentingCreateRequest {

    private Long id;
}
