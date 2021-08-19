package com.obss.jss.onlinemarketplace.service.impl;

import com.obss.jss.onlinemarketplace.converter.UserConverter;
import com.obss.jss.onlinemarketplace.dto.UserDTO;
import com.obss.jss.onlinemarketplace.exception.UserNotFoundException;
import com.obss.jss.onlinemarketplace.model.User;
import com.obss.jss.onlinemarketplace.repository.ProductRepository;
import com.obss.jss.onlinemarketplace.repository.UserRepository;
import com.obss.jss.onlinemarketplace.service.BlackListService;
import com.obss.jss.onlinemarketplace.service.FavoriteListService;
import com.obss.jss.onlinemarketplace.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final FavoriteListService favoriteListService;
    private final BlackListService blackListService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ProductRepository productRepository,
                           FavoriteListService favoriteListService, BlackListService blackListService) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.favoriteListService = favoriteListService;
        this.blackListService = blackListService;
    }

    @Override
    public User findByUsername(String username) {
        Objects.requireNonNull(username, "username cannot be null");
        User user = userRepository.findByUsername(username);
        if (user == null) {
            LOGGER.error("Not found user with username: {}", username);
            throw new UserNotFoundException();
        }
        return user;
    }

    public Page<UserDTO> getUsers(String name, String email,

                                  Pageable paged) {

        List<User> users = userRepository.findAll((Specification<User>) (root, cq, cb) -> {
            Predicate p = cb.conjunction();
            if (Objects.nonNull(name) && !(name.equals("null"))) {
                p = cb.and(p, cb.like(root.get("username"), "%" + name + "%"));
            }
            if (Objects.nonNull(email) && !(email.equals("null"))) {
                p = cb.and(p, cb.like(root.get("email"), "%" + email + "%"));
            }
           // p = cb.and(p, cb.like(root.get("roles").get("name"), "%" + "ROLE_USER"+ "%"));

            cq.orderBy(cb.desc(root.get("username")), cb.asc(root.get("id")));
            return p;
        }, paged).getContent();
        Page<UserDTO> page = new PageImpl<>(UserConverter.convertToUserDTO(users));
        return page;
        //return StudentConverter.convertToStudentDTO(students);
    }

    @Override
    public User createNewUser(User user) {
        Objects.requireNonNull(user, "User cannot be null");
        Objects.requireNonNull(user.getUsername(), "User username cannot be null");
        Objects.requireNonNull(user.getEmail(), "User email cannot be null");
        Objects.requireNonNull(user.getPassword(), "User password cannot be null");


        LOGGER.info("User created with username  and email and roles: {} ",
                user.getUsername() + " " + user.getEmail() );

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, String username, String email) {

        User updatedUser = userRepository.getById(userId);
        Objects.requireNonNull(userId, "User cannot be null");
        Objects.requireNonNull(username, "User username cannot be null");
        Objects.requireNonNull(email, "User email cannot be null");

        updatedUser.setUsername(username);
        updatedUser.setEmail(email);
        return userRepository.save(updatedUser);
    }


    @Override
    public boolean existsByUsername(String username) {
        Objects.requireNonNull(username, "username cannot be null");
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        Objects.requireNonNull(email, "email cannot be null");
        return userRepository.existsByEmail(email);
    }

    @Override
    public User deleteUser(Long id) {
        Objects.requireNonNull(id, "Id cannot be null");

        if (userRepository.existsById(id)) {
            User deletedUser = userRepository.findById(id).get();
            LOGGER.info("User deleted by id: {}", deletedUser.getId());
            userRepository.deleteById(id);
            return deletedUser;
        } else {
            return null;
        }

    }

    @Override
    public Optional<User> findById(Long id) {

        Objects.requireNonNull(id, "Id cannot be null");
        return userRepository.findById(id);
    }
}
