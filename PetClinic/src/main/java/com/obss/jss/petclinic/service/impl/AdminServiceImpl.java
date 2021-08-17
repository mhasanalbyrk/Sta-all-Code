package com.obss.jss.petclinic.service.impl;

import com.obss.jss.petclinic.model.Admin;
import com.obss.jss.petclinic.repository.AdminRepository;
import com.obss.jss.petclinic.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Override
    public Admin createNewAdmin(Admin admin) {
        adminRepository.save(admin);
        return null;
    }

    @Override
    public List<Admin> searchAdminByName(String name) {
        return null;
    }

    @Override
    public Admin findById(Long id) {
        return null;
    }
}
