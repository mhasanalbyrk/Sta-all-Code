package com.obss.jss.onlinemarketplace.converter;

import com.obss.jss.onlinemarketplace.dto.SellerDTO;
import com.obss.jss.onlinemarketplace.model.Seller;

import java.util.List;
import java.util.stream.Collectors;

public class SellerConverter {
    public static List<SellerDTO> convertToSellerDTO(List<Seller> sellers) {
        return sellers.stream().map(s ->
                SellerDTO.builder()
                        .id(s.getId())
                        .username(s.getUsername())
                        .email(s.getEmail())
                        .productList(ProductConventer.convertToProductDTO(s.getProductList()))
                        .build()
        ).collect(Collectors.toList());
    }

    public static Seller convertToSeller(SellerDTO sellerDTO) {
        Seller seller = new Seller();
        seller.setUsername(sellerDTO.getUsername());
        seller.setEmail(sellerDTO.getEmail());
        seller.setId(sellerDTO.getId());

        return seller;
    }
}
