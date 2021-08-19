package com.obss.jss.onlinemarketplace.service;

import com.obss.jss.onlinemarketplace.dto.SellerDTO;
import com.obss.jss.onlinemarketplace.model.Product;
import com.obss.jss.onlinemarketplace.model.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface SellerService {
    Seller createNewSeller(String username, String email);

    Optional<Seller> findById(Long id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    SellerDTO deleteSeller(Long id);

    Seller updateSeller(Long sellerId, String username, String email);

    Seller findByUsername(String username);

    Page<SellerDTO> getSellers(String name, String email,
                               Pageable paged);

    Product addToList(Product product, Long sellerId);

    Product removeFromList(Long productId, Long sellerId);

}
