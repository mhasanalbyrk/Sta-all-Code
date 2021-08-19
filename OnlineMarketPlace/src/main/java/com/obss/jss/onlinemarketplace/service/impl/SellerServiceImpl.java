package com.obss.jss.onlinemarketplace.service.impl;

import com.obss.jss.onlinemarketplace.converter.SellerConverter;
import com.obss.jss.onlinemarketplace.dto.SellerDTO;
import com.obss.jss.onlinemarketplace.exception.UsernameExistsException;
import com.obss.jss.onlinemarketplace.model.Product;
import com.obss.jss.onlinemarketplace.model.Seller;
import com.obss.jss.onlinemarketplace.repository.ProductRepository;
import com.obss.jss.onlinemarketplace.repository.SellerRepository;
import com.obss.jss.onlinemarketplace.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository, ProductRepository productRepository) {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Product addToList(Product product, Long sellerId) {
        Objects.requireNonNull(sellerId, "Id cannot be null");
        Objects.requireNonNull(product, "Product cannot be null");

        Optional<Seller> seller = sellerRepository.findById(sellerId);
        product.setSeller(seller.get());
        //Product product = productRepository.findProductById(productId);
        if (seller.get().getProductList() != null) {
            seller.get().getProductList().add(product);
        } else {

            seller.get().setProductList(new ArrayList<Product>());
            seller.get().getProductList().add(product);
        }
        sellerRepository.save(seller.get());
        return product;
    }

    @Override
    public Product removeFromList(Long productId, Long sellerId) {
        Objects.requireNonNull(sellerId, "Id cannot be null");
        Objects.requireNonNull(productId, "Id cannot be null");
        Optional<Seller> seller = sellerRepository.findById(sellerId);
        Product product = productRepository.findProductById(productId);
        if (seller.get().getProductList() != null) {
            seller.get().getProductList().remove(product);
            sellerRepository.save(seller.get());
            productRepository.deleteById(productId);
            return product;
        } else {

            return null;
        }
    }

    @Override
    public Seller createNewSeller(String username, String email) {
        if ( existsByUsername(username)){
            throw new UsernameExistsException();
        }
        if (existsByEmail(email) ){
            throw new UsernameExistsException();
        }
        Seller newSeller = Seller.builder()
                .username(username)
                .email(email).build();
        return sellerRepository.save(newSeller);
    }

    @Override
    public Optional<Seller> findById(Long id) {
        return sellerRepository.findById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        Objects.requireNonNull(username, "username cannot be null");
        return sellerRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        Objects.requireNonNull(email, "email cannot be null");
        return sellerRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public SellerDTO deleteSeller(Long id) {
        Seller deletedSeller = sellerRepository.findById(id).get();
        if (deletedSeller.getProductList().size() != 0) {
            deletedSeller.getProductList().removeAll(deletedSeller.getProductList());
            productRepository.deleteBySellerId(id);
        }
        sellerRepository.save(deletedSeller);
        sellerRepository.deleteById(id);
        return SellerConverter.convertToSellerDTO(List.of(deletedSeller)).get(0);
    }

    @Override
    public Seller updateSeller(Long sellerId, String username, String email) {
        Seller updatedSeller = sellerRepository.findById(sellerId).get();
        updatedSeller.setEmail(email);
        updatedSeller.setUsername(username);
        return sellerRepository.save(updatedSeller);
    }

    @Override
    public Seller findByUsername(String username) {
        return null;
    }

    @Override
    public Page<SellerDTO> getSellers(String name, String email, Pageable paged) {
        List<Seller> sellers = sellerRepository.findAll((Specification<Seller>) (root, cq, cb) -> {
            Predicate p = cb.conjunction();
            if (Objects.nonNull(name) && !(name.equals("null"))) {
                p = cb.and(p, cb.like(root.get("username"), "%" + name + "%"));
            }
            if (Objects.nonNull(email) && !(email.equals("null"))) {
                p = cb.and(p, cb.like(root.get("email"), "%" + email + "%"));
            }

            cq.orderBy(cb.desc(root.get("username")), cb.asc(root.get("id")));
            return p;
        }, paged).getContent();
        Page<SellerDTO> page = new PageImpl<>(SellerConverter.convertToSellerDTO(sellers));
        return page;
        //return StudentConverter.convertToStudentDTO(students);
    }
}
