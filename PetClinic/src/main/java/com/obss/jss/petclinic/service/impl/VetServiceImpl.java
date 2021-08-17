package com.obss.jss.petclinic.service.impl;

import com.obss.jss.petclinic.model.Vet;
import com.obss.jss.petclinic.repository.VetRepository;
import com.obss.jss.petclinic.service.AdminService;
import com.obss.jss.petclinic.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class VetServiceImpl implements VetService {
    private VetRepository vetRepository;
    private AdminService adminService;

    @Autowired
    public VetServiceImpl(VetRepository vetRepository, AdminService adminService) {
        this.vetRepository = vetRepository;
        this.adminService = adminService;
    }

    @Override
    public Vet createNewVet(Vet vet) {
        Objects.requireNonNull(vet, "Vet cannot be null");
        Objects.requireNonNull(vet.getName(), "Vet name cannot be null");
        return vetRepository.save(vet);
    }

    @Override
    public List<Vet> searchVetByName(String name) {
        return null;
    }

    @Override
    public Vet findById(Long id) {
        return null;
    }
}
