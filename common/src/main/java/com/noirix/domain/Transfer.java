package com.noirix.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

// Mapping entity transfer for transfer's table in database.

@Data
@Entity
@Table(name = "c_transfers")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_renting")
    @JsonBackReference
    private Renting renting;

    @Column (name = "start_place")
    private String startPlace;

    @Column (name = "end_place")
    private String endPlace;

    @Column (name = "start_odometer_reading")
    private Double startOdometerReading;

    @Column (name = "end_odometer_reading")
    private Double endOdometerReading;

    @Column (name = "start_amount_of_fuel")
    private Double startAmountOfFuel;

    @Column (name = "end_amount_of_fuel")
    private Double endAmountOfFuel;

    public Transfer() {
    }

    public Transfer(Renting renting, String startPlace, String endPlace, Double startOdometerReading,
                    Double endOdometerReading, Double startAmountOfFuel, Double endAmountOfFuel) {
        this.renting = renting;
        this.startPlace = startPlace;
        this.endPlace = endPlace;
        this.startOdometerReading = startOdometerReading;
        this.endOdometerReading = endOdometerReading;
        this.startAmountOfFuel = startAmountOfFuel;
        this.endAmountOfFuel = endAmountOfFuel;
    }
}
