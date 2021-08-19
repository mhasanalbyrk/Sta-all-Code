package com.obss.jss.onlinemarketplace.dto;

import com.obss.jss.onlinemarketplace.model.BlackList;
import com.obss.jss.onlinemarketplace.model.FavoriteList;
import com.obss.jss.onlinemarketplace.model.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder
@Getter
public class SellerDTO {
    private Long id;

    private String username;

    private String email;

    private List<ProductDTO> productList;
}
