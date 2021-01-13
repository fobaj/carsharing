package com.noirix.controller.responces;

import lombok.AllArgsConstructor;
import lombok.Data;

// An additional class for message in case an error.

@Data
@AllArgsConstructor
public class ErrorMessage {

    private Long errorId;

    private String errorMessage;
}
