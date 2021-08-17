package com.obss.jss.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Speciality extends BaseEntity{
    @NotNull
    private String name;
    @Column(length = 500)
    private String description;
}
