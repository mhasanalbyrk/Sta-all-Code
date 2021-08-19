package com.obss.jss.onlinemarketplace.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.obss.jss.onlinemarketplace.model.BlackList;
import com.obss.jss.onlinemarketplace.model.FavoriteList;
import com.obss.jss.onlinemarketplace.model.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
public class UserDTO {

    private Long id;

    private String username;

    private String email;

    private Set<Role> roles = new HashSet<>();

    private BlackList blackList;

    private FavoriteList favoriteList;
}
