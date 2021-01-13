package com.noirix.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

import java.sql.Timestamp;

// Mapping entity request for request's table in database.

@Data
@Entity
@Table(name = "c_requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_car")
    @JsonBackReference
    private Car car;

    @Column (name = "start_date_and_time")
    private Timestamp startDateAndTime;

    @Column (name = "end_date_and_time")
    private Timestamp endDateAndTime;

    public Request() {
    }

    public Request(Car car, Timestamp startDateAndTime, Timestamp endDateAndTime) {
        this.car = car;
        this.startDateAndTime = startDateAndTime;
        this.endDateAndTime = endDateAndTime;
    }
}
