package com.noirix.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@EqualsAndHashCode(exclude = {
        "transfer"
})
@Table(name = "c_renting")
public class Renting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double cost;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column (name = "is_deleted")
    private Boolean isDeleted = false;

    @OneToOne(mappedBy = "renting", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Transfer transfer;

    public Renting() {
    }

    public Renting(Double cost, Timestamp created, Timestamp changed, Boolean isDeleted) {
        this.cost = cost;
        this.created = created;
        this.changed = changed;
        this.isDeleted = isDeleted;
    }
}
