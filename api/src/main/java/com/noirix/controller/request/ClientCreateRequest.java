package com.noirix.controller.request;

import com.noirix.domain.Gender;
import com.noirix.domain.Repairing;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Data
public class ClientCreateRequest {

    private String name;

    private String surname;

    private Gender gender = Gender.NOT_SELECTED;

    private Date birthDate;

    private String country;

    private String passport;

    private String telephone;

    private String eMail;

    private String login;

    private String password;

    private Timestamp created;

    private Timestamp changed;

    private Boolean isDeleted = false;

    private Set<Repairing> repairings = Collections.emptySet();
}
