package com.obss.jss.onlinemarketplace.service;

import com.obss.jss.onlinemarketplace.model.FavoriteList;

import java.util.List;
import java.util.Optional;

public interface FavoriteListService {
    FavoriteList createFavoriteList(FavoriteList favoriteList);
    Optional<FavoriteList> findById(Long id);

    boolean addFavorite(Long userId, Long productId);
    boolean removeFavorite(Long userId, Long productId);
}
