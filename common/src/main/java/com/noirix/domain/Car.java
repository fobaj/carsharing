package com.noirix.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = {
        "requests"
})
@Table (name = "c_cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String brand;

    @Column
    private String model;

    @Column (name = "year_of_issue")
    private Integer yearOfIssue;

    @Column
    private String color;

    @Column
    private String vin;

    @Column (name = "registration_plate")
    private String registrationPlate;

    @Column (name = "number_of_seats")
    private Integer numberOfSeats;

    @Column
    private Double rate;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column (name = "is_deleted")
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<Request> requests = Collections.emptySet();

}
