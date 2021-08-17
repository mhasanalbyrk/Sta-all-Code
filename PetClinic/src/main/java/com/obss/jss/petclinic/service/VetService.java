package com.obss.jss.petclinic.service;


import com.obss.jss.petclinic.model.Vet;

import java.util.List;

public interface VetService {
    Vet createNewVet(Vet vet);

    List<Vet> searchVetByName(String name);

    Vet findById(Long id);
}
