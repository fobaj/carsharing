package com.noirix.controller.request;


import com.noirix.domain.Transfer;
import lombok.Data;

import java.sql.Timestamp;

// A class to use as a request in the Rentingcontroller.

@Data
public class RentingCreateRequest {

    private Double cost;

    private Timestamp created;

    private Timestamp changed;

    private Boolean isDeleted = false;

    private Transfer transfer;
}
