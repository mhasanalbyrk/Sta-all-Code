package com.obss.jss.petclinic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
public class Admin extends Person{
    @OneToMany(fetch = FetchType.LAZY)
    private List<Role> roles;
}
