package com.obss.jss.onlinemarketplace.repository;



import com.obss.jss.onlinemarketplace.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Product findProductById(Long id);

   // List<Product> findAll(Long id);

    Product findProductByName(String name);
    Page<Product> findProductsByCategoryLike(String category, Pageable pageable);
    void deleteById(Long id);

    void deleteBySellerId(Long sellerId);
}
