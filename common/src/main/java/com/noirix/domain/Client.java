package com.noirix.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = {
        "repairings"
})
@Table (name = "c_clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    @Enumerated (EnumType.STRING)
    private Gender gender = Gender.NOT_SELECTED;

    @Column (name = "birth_date")
    @Temporal (TemporalType.DATE)
    private Date birthDate;

    @Column
    private String country;

    @Column
    private String passport;

    @Column
    private String telephone;

    @Column (name = "e-mail")
    private String eMail;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column (name = "is_deleted")
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<Repairing> repairings = Collections.emptySet();

}
