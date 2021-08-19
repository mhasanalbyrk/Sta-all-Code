package com.obss.jss.onlinemarketplace.repository;

import com.obss.jss.onlinemarketplace.model.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlackListRepository extends JpaRepository<BlackList, Long> {
    List<BlackList> findBlackListById(Long id);
}
