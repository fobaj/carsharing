package com.noirix.controller.request;

import lombok.Data;

// A class to use as a request in the Clientcontroller.

@Data
public class ClientChangeRequest extends ClientCreateRequest {

    private Long id;
}
