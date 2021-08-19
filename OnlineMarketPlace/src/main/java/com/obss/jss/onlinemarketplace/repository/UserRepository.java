package com.obss.jss.onlinemarketplace.repository;


import com.obss.jss.onlinemarketplace.model.Product;
import com.obss.jss.onlinemarketplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void deleteById(Long id);

}
