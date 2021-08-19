package com.obss.jss.onlinemarketplace.service.impl;

import com.obss.jss.onlinemarketplace.converter.ProductConventer;
import com.obss.jss.onlinemarketplace.dto.ProductDTO;
import com.obss.jss.onlinemarketplace.model.Product;
import com.obss.jss.onlinemarketplace.repository.ProductRepository;
import com.obss.jss.onlinemarketplace.service.FavoriteListService;
import com.obss.jss.onlinemarketplace.service.ProductService;
import com.obss.jss.onlinemarketplace.service.SellerService;
import com.obss.jss.onlinemarketplace.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    public static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    private final SellerService sellerService;

    private final UserService userService;

    private final FavoriteListService favoriteListService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, SellerService sellerService, UserService userService, FavoriteListService favoriteListService) {
        this.productRepository = productRepository;
        this.sellerService = sellerService;
        this.userService = userService;
        this.favoriteListService = favoriteListService;
    }

    @Override
    public Optional<Product> findById(Long id) {

        return productRepository.findById(id);
    }

    @Override
    public ProductDTO updateProduct(Long productId,
                                 String name,
                                 String category,
                                 String description,
                                 Long stock,
                                 Long price) {

        Product updatedProduct = productRepository.findProductById(productId);
        updatedProduct.setPrice(price);
        updatedProduct.setCategory(category);
        updatedProduct.setDescription(description);
        updatedProduct.setName(name);
        productRepository.save(updatedProduct);

        ProductDTO productDTO = ProductConventer
                .convertToProductDTO(List.of(updatedProduct)).get(0);


        return productDTO;
    }

    @Override
    public Product createNewProduct(Long sellerId,
                                    String name,
                                    String category,
                                    String description,
                                    Long stock,
                                    Long price) {
        Objects.requireNonNull(name, "Product name cannot be null");
        Objects.requireNonNull(category, "Product category cannot be null");
        Objects.requireNonNull(description, "Product description cannot be null");
        Objects.requireNonNull(stock, "Product stock cannot be null");
        Objects.requireNonNull(price, "Product price cannot be null");
        LOGGER.info("New product created: {}", name);
        Product newProduct = Product.builder()
                .name(name)
                .category(category)
                .description(description)
                .price(price)
                .inStock(stock)
                .build();



        return sellerService.addToList(productRepository.save(newProduct), sellerId);

    }

    @Override
    public ProductDTO deleteProduct(Long id) {
        Objects.requireNonNull(id, "Id cannot be null");
        if (productRepository.existsById(id)) {
            Product deletedProduct = productRepository.findProductById(id);
            sellerService.removeFromList(id, deletedProduct.getSeller().getId());
            //favoriteListService.removeFavorite()
            //productRepository.deleteById(id);
            LOGGER.info("Product deleted id: {}", deletedProduct.getId());
            return ProductConventer.convertToProductDTO(List.of(deletedProduct)).get(0);
        } else {
            return null;
        }

    }

    //int fromRating, int toRating,Long fromPrice, Long toPrice,
    public Page<ProductDTO> getProducts(String name, String description,
                                     String category,
                                     Pageable paged) {


        //Pageable fullPaged = PageRequest.ofSize(paged.getPageSize());

        List<Product> products = productRepository.findAll((Specification<Product>) (root, cq, cb) -> {
            Predicate p = cb.conjunction();
            if (Objects.nonNull(name) && !(name.equals("null"))) {
                p = cb.and(p, cb.like(root.get("name"), "%" + name + "%"));
            }
            if (Objects.nonNull(description) && !(description.equals("null"))) {
                p = cb.and(p, cb.like(root.get("description"), "%" + description + "%"));
            }
            if (Objects.nonNull(category) && !(category.equals("null"))) {
                p = cb.and(p, cb.like(root.get("category"), "%" + category + "%"));
            }
            cq.orderBy(cb.desc(root.get("name")), cb.asc(root.get("id")));
            return p;
        },  paged).getContent();
        for (Product product : products) {
            product.getSeller().setProductList(null);
        }
        //PagedListHolder fullPage = new PagedListHolder(ProductConventer.convertToProductDTO(products));
       // fullPage.setPageSize(paged.getPageSize());
       // fullPage.setPage(paged.getPageNumber());
        Page<ProductDTO> page = new PageImpl<>(ProductConventer.convertToProductDTO(products));
        return page;
    }
}
