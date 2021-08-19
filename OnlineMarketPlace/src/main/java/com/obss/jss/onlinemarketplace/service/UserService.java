package com.obss.jss.onlinemarketplace.service;

import com.obss.jss.onlinemarketplace.dto.UserDTO;
import com.obss.jss.onlinemarketplace.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    User createNewUser(User user);

    Optional<User> findById(Long id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User deleteUser(Long id);

    User updateUser(Long userId, String username, String email);

    User findByUsername(String username);

    Page<UserDTO> getUsers(String name, String email,
                           Pageable paged);

}
