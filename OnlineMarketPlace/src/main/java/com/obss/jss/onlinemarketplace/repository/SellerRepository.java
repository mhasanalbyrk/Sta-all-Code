package com.obss.jss.onlinemarketplace.repository;

import com.obss.jss.onlinemarketplace.model.Seller;
import com.obss.jss.onlinemarketplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SellerRepository extends JpaRepository<Seller, Long>, JpaSpecificationExecutor<Seller> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
