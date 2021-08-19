package com.obss.jss.onlinemarketplace.dto;

import com.obss.jss.onlinemarketplace.model.Seller;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductDTO {
    private Long id;

    private String name;

    private String description;

    private String category;

    private int rating;

    private Long price;

    private Long inStock;

    private Long sellerId;
    private String sellerName;
}
