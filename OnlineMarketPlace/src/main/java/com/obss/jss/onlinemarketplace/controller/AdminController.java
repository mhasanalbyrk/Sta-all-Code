package com.obss.jss.onlinemarketplace.controller;

import com.obss.jss.onlinemarketplace.dto.SellerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @PostMapping("/addseller")
    public ResponseEntity<?> addSeller(SellerDTO sellerDTO) {

        return ResponseEntity.ok(true);
    }

}
