package com.obss.jss.petclinic.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@MappedSuperclass
public class Person extends BaseEntity {

    @NotNull
    private String name;
    @NotNull
    private String surname;
    @Column(length = 500)
    private String address;
    @NotNull
    private String city;

    public Person() {

        super();

    }
}
