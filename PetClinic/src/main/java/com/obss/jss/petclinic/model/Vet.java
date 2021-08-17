package com.obss.jss.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;
@Entity
public class Vet extends Person {

    @OneToMany(fetch = FetchType.LAZY)
    private List<Speciality> specialityList;
}
