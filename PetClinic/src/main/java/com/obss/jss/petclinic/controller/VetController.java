package com.obss.jss.petclinic.controller;

import com.obss.jss.petclinic.model.Vet;
import com.obss.jss.petclinic.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/vet")
public class VetController {
    private VetService vetService;
    @Autowired
    public VetController(VetService vetService){
        this.vetService = vetService;
    }
    @PostMapping("/create")
    public Vet createNewVet(@RequestBody  Vet vet){
        return vetService.createNewVet(vet);
    }

    public void findById(Long id){

    }


}
