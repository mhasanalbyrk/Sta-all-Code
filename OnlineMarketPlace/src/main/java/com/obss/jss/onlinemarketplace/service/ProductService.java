package com.obss.jss.onlinemarketplace.service;

import com.obss.jss.onlinemarketplace.dto.ProductDTO;
import com.obss.jss.onlinemarketplace.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface ProductService {
    Product createNewProduct(Long sellerId,
                             String name,
                             String category,
                             String description,
                             Long stock,
                             Long price);

    Optional<Product> findById(Long id);

    ProductDTO deleteProduct(Long id);

    ProductDTO updateProduct( Long productId,
     String name,
     String category,
     String description,
     Long stock,
    Long price);

    Page<ProductDTO> getProducts(String name, String description,
                                 String category,
                                 Pageable paged);
}
