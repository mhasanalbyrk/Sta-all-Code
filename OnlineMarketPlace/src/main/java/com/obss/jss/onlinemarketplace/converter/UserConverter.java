package com.obss.jss.onlinemarketplace.converter;

import com.obss.jss.onlinemarketplace.dto.ProductDTO;
import com.obss.jss.onlinemarketplace.dto.UserDTO;
import com.obss.jss.onlinemarketplace.model.Product;
import com.obss.jss.onlinemarketplace.model.Role;
import com.obss.jss.onlinemarketplace.model.Seller;
import com.obss.jss.onlinemarketplace.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {
    public static List<UserDTO> convertToUserDTO(List<User> users) {


        List<UserDTO> userDTOS = users.stream().map(s ->
                UserDTO.builder()
                        .id(s.getId())
                        .username(s.getUsername())
                        .email(s.getEmail())
                        .blackList(s.getBlackList())
                        .favoriteList(s.getFavoriteList())
                        .roles(s.getRoles())
                        .build()
        ).collect(Collectors.toList());

        for (UserDTO dto : userDTOS) {
            if (dto.getFavoriteList() != null) {
                dto.getBlackList().setOwner(null);
                for (Seller seller : dto.getBlackList().getSellerBlackList()) {
                    seller.setProductList(null);
                }
            }
            if (dto.getFavoriteList() != null) {
                dto.getFavoriteList().setOwner(null);
                for (Product product : dto.getFavoriteList().getProductFavList()) {
                    product.setSeller(null);
                }
            }
//            Role[] roles = new Role[1];
//            // roles = dto.getRoles().toArray(roles);
//            if (dto.getRoles().toArray(roles)[0].getName().equals("ROLE_ADMIN")){
//                userDTOS.remove(dto);
//            }
        }
        return userDTOS;
    }

    public static User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setBlackList(userDTO.getBlackList());
        user.setFavoriteList(userDTO.getFavoriteList());
        user.setRoles(userDTO.getRoles());
        user.setId(userDTO.getId());

        return user;
    }
}
