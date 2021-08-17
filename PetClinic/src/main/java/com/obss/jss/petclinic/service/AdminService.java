package com.obss.jss.petclinic.service;

import com.obss.jss.petclinic.model.Admin;

import java.util.List;

public interface AdminService {

    Admin createNewAdmin(Admin admin);

    List<Admin> searchAdminByName(String name);

    Admin findById(Long id);
}
