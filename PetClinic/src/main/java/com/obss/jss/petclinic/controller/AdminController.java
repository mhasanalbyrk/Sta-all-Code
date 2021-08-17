package com.obss.jss.petclinic.controller;

import com.obss.jss.petclinic.model.Admin;
import com.obss.jss.petclinic.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    private AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping("/create")
    public Admin createNewAdmin(@RequestBody Admin admin){
        return adminService.createNewAdmin(admin);
    }
}
