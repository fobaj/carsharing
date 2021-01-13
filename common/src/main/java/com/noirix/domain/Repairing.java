package com.noirix.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

// Mapping entity repairing for repairing's table in database.

@Data
@Entity
@Table(name = "c_repairing")
public class Repairing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_client")
    @JsonBackReference
    private Client client;

    @Column
    private String damage;

    @Column
    private Double cost;

    public Repairing() {
    }

    public Repairing(Client client, String damage, Double cost) {
        this.client = client;
        this.damage = damage;
        this.cost = cost;
    }
}
