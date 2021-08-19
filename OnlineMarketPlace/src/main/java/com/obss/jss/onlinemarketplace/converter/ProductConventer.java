package com.obss.jss.onlinemarketplace.converter;

import com.obss.jss.onlinemarketplace.dto.ProductDTO;
import com.obss.jss.onlinemarketplace.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductConventer {

    public static List<ProductDTO> convertToProductDTO(List<Product> students) {
        return students.stream().map(s ->
                ProductDTO.builder()
                        .id(s.getId())
                        .name(s.getName())
                        .category(s.getCategory())
                        .description(s.getDescription())
                        .price(s.getPrice())
                        .rating(s.getRating())
                        .sellerId(s.getSeller().getId())
                        .sellerName(s.getSeller().getUsername())
                        .inStock(s.getInStock())
                        .build()
        ).collect(Collectors.toList());
    }

    public static Product convertToSProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setCategory(productDTO.getCategory());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setRating(productDTO.getRating());
        product.setId(productDTO.getId());

        return product;
    }

}
