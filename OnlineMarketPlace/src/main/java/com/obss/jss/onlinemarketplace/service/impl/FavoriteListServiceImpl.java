package com.obss.jss.onlinemarketplace.service.impl;

import com.obss.jss.onlinemarketplace.model.FavoriteList;
import com.obss.jss.onlinemarketplace.model.Product;
import com.obss.jss.onlinemarketplace.model.User;
import com.obss.jss.onlinemarketplace.repository.FavoriteListRepository;
import com.obss.jss.onlinemarketplace.repository.ProductRepository;
import com.obss.jss.onlinemarketplace.repository.UserRepository;
import com.obss.jss.onlinemarketplace.service.FavoriteListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FavoriteListServiceImpl implements FavoriteListService {
    public static final Logger LOGGER = LoggerFactory.getLogger(FavoriteListServiceImpl.class);

    private final FavoriteListRepository favoriteListRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public FavoriteListServiceImpl(UserRepository userRepository, ProductRepository productRepository, FavoriteListRepository favoriteListRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.favoriteListRepository = favoriteListRepository;

    }

    @Override
    public boolean removeFavorite(Long userId, Long productId) {
        Objects.requireNonNull(userId, "Id cannot be null");
        Objects.requireNonNull(productId, "Id cannot be null");
        Optional<User> user = userRepository.findById(userId);
        Product product = productRepository.findProductById(productId);
        if (user.get().getFavoriteList() != null) {
            if (user.get().getFavoriteList().getProductFavList().contains(product)){
                user.get().getFavoriteList().getProductFavList().remove(product);
            }
            else {
                return false;
            }
            userRepository.save(user.get());
            return true;
        } else {

            return false;
        }
    }

    @Override
    @Transactional
    public boolean addFavorite(Long userId, Long productId) {
        Objects.requireNonNull(userId, "Id cannot be null");
        Objects.requireNonNull(productId, "Id cannot be null");
        Optional<User> user = userRepository.findById(userId);
        Product product = productRepository.findProductById(productId);
        if (user.get().getFavoriteList() != null) {
            if (user.get().getFavoriteList().getProductFavList().contains(product)){
                return false;
            }
            user.get().getFavoriteList().getProductFavList().add(product);
        } else {
            FavoriteList favoriteList = new FavoriteList();

            favoriteList.setOwner(user.get());
            favoriteList.setProductFavList(new ArrayList<Product>());
            favoriteList.getProductFavList().add(product);
            user.get().setFavoriteList(favoriteList);
        }
        userRepository.save(user.get());
        return true;

    }

    @Override
    public FavoriteList createFavoriteList(FavoriteList favoriteList) {
        return favoriteListRepository.save(favoriteList);
    }


    @Override
    public Optional<FavoriteList> findById(Long id) {
        return favoriteListRepository.findById(id);
    }
}
