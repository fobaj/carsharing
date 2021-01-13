package com.noirix.controller.request;

import lombok.Data;

// A class to use as a request in the Carcontroller.

@Data
public class CarChangeRequest extends CarCreateRequest{

    private Long id;
}
