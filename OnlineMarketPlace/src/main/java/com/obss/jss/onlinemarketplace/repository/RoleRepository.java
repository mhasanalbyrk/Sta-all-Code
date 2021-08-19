package com.obss.jss.onlinemarketplace.repository;

import com.obss.jss.onlinemarketplace.model.BlackList;
import com.obss.jss.onlinemarketplace.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findRoleListById(Long id);

    Role findByName(String name);
}
