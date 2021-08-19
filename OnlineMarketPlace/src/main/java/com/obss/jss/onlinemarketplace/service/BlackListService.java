package com.obss.jss.onlinemarketplace.service;

import com.obss.jss.onlinemarketplace.model.BlackList;

import java.util.List;

public interface BlackListService {

    BlackList findById(Long id);
    boolean blackListed(Long userId, Long sellerId);
    boolean removeFromBlackList(Long userId, Long sellerId);
}
