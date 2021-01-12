package com.noirix.controller.request;


import com.noirix.domain.Transfer;
import lombok.Data;


import java.sql.Timestamp;

@Data
public class RentingCreateRequest {

    private Double cost;

    private Timestamp created;

    private Timestamp changed;

    private Boolean isDeleted = false;

    private Transfer transfer;
}
