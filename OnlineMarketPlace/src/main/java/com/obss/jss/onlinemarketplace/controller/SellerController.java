package com.obss.jss.onlinemarketplace.controller;

import com.obss.jss.onlinemarketplace.model.Product;
import com.obss.jss.onlinemarketplace.service.ProductService;
import com.obss.jss.onlinemarketplace.service.SellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller")
public class SellerController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SellerController.class);
    private final SellerService sellerService;
    private final ProductService productService;

    @Autowired
    public SellerController(SellerService sellerService, ProductService productService) {
        this.sellerService = sellerService;
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(@RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                    @RequestParam(name = "name", required = false) String name,
                                    @RequestParam(name = "email", required = false) String email) {

        Pageable paged = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.ok(sellerService.getSellers(name, email, paged));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUser(@RequestParam("id") String id) {

        return ResponseEntity.ok(sellerService.deleteSeller(Long.parseLong(id)));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addSeller(@RequestParam("username") String username,
                                       @RequestParam("email") String email) {


        return ResponseEntity.ok(sellerService.createNewSeller(username, email));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateSeller(@RequestParam(name = "sellerId") String sellerId,
                                          @RequestParam(name = "username") String username,
                                          @RequestParam(name = "email") String email) {


        return ResponseEntity.ok(sellerService.updateSeller(Long.parseLong(sellerId), username, email));
    }

}
