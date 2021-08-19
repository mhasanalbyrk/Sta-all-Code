package com.obss.jss.onlinemarketplace.controller;

import com.obss.jss.onlinemarketplace.converter.ProductConventer;
import com.obss.jss.onlinemarketplace.model.Product;
import com.obss.jss.onlinemarketplace.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable String id) {

        return productService.findById(Long.parseLong(id));
    }

    @PostMapping("/create")
    public ResponseEntity<?> newProduct(@RequestParam(name = "sellerId") String sellerId,
                                        @RequestParam(name = "name") String name,
                                        @RequestParam(name = "category") String category,
                                        @RequestParam(name = "description") String description,
                                        @RequestParam(name = "stock") String stock,
                                        @RequestParam(name = "price") String price) {
         Product product = productService.createNewProduct(Long.parseLong(sellerId), name, category,
                description, Long.parseLong(stock), Long.parseLong(price));

        return ResponseEntity.ok(ProductConventer.convertToProductDTO(List.of(product)).get(0));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(@RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                    @RequestParam(name = "name", required = false) String name,
                                    @RequestParam(name = "category", required = false) String category,
                                    @RequestParam(name = "description", required = false) String description) {

        Pageable paged = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.ok(productService.getProducts(name, description, category, paged));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteProduct(@RequestParam("id") String id) {


        return ResponseEntity.ok(productService.deleteProduct(Long.parseLong(id)));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateProduct(@RequestParam(name = "productId") String productId,
                                 @RequestParam(name = "name") String name,
                                 @RequestParam(name = "category") String category,
                                 @RequestParam(name = "description") String description,
                                 @RequestParam(name = "stock") String stock,
                                 @RequestParam(name = "price") String price) {


        return ResponseEntity.ok(productService.updateProduct(Long.parseLong(productId), name, category, description,
                Long.parseLong(stock), Long.parseLong(price)));
    }
}
