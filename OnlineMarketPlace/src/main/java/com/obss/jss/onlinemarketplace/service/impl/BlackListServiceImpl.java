package com.obss.jss.onlinemarketplace.service.impl;

import com.obss.jss.onlinemarketplace.model.BlackList;
import com.obss.jss.onlinemarketplace.model.Seller;
import com.obss.jss.onlinemarketplace.model.User;
import com.obss.jss.onlinemarketplace.repository.BlackListRepository;
import com.obss.jss.onlinemarketplace.repository.SellerRepository;
import com.obss.jss.onlinemarketplace.repository.UserRepository;
import com.obss.jss.onlinemarketplace.service.BlackListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
public class BlackListServiceImpl implements BlackListService {
    public static final Logger LOGGER = LoggerFactory.getLogger(BlackListServiceImpl.class);
    private final BlackListRepository blackListRepository;

    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;


    @Autowired
    public BlackListServiceImpl(BlackListRepository blackListRepository, UserRepository userRepository, SellerRepository sellerRepository) {
        this.blackListRepository = blackListRepository;
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public boolean blackListed(Long userId, Long sellerId) {
        Objects.requireNonNull(userId, "Id cannot be null");

        Objects.requireNonNull(sellerId, "Id cannot be null");
        Optional<User> user = userRepository.findById(userId);
        Optional<Seller> seller = sellerRepository.findById(sellerId);

        if (user.get().getBlackList() != null) {
            if (user.get().getBlackList().getSellerBlackList().contains(seller)){
                return false;
            }
            user.get().getBlackList().getSellerBlackList().add(seller.get());
        } else {
            BlackList blackList = new BlackList();
            blackList.setOwner(user.get());
            blackList.setSellerBlackList(new ArrayList<Seller>());
            blackList.getSellerBlackList().add(seller.get());
            user.get().setBlackList(blackList);
        }
        userRepository.save(user.get());
        return true;

    }

    @Override
    public boolean removeFromBlackList(Long userId, Long sellerId) {
        Objects.requireNonNull(userId, "Id cannot be null");
        Objects.requireNonNull(sellerId, "Id cannot be null");

        Optional<User> user = userRepository.findById(userId);
        Optional<Seller> seller = sellerRepository.findById(sellerId);

        if (user.get().getBlackList() != null) {
            if (user.get().getBlackList().getSellerBlackList().contains(seller.get())){
                user.get().getBlackList().getSellerBlackList().remove(seller.get());
            }else {
                return false;
            }

            userRepository.save(user.get());
            LOGGER.info("Seller " + sellerId + "removed from blacklist");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public BlackList findById(Long id) {
        return null;
    }
}
