package com.obss.jss.onlinemarketplace.repository;

import com.obss.jss.onlinemarketplace.model.FavoriteList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteListRepository extends JpaRepository<FavoriteList, Long> {
    List<FavoriteList> findFavoriteListById(Long id);

    FavoriteList findFavoriteListByOwnerId(Long ownerId);
}
