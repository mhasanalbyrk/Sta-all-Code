package com.obss.jss.onlinemarketplace.service.impl;

import com.obss.jss.onlinemarketplace.model.User;
import com.obss.jss.onlinemarketplace.security.MyUserDetails;
import com.obss.jss.onlinemarketplace.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private final UserService userService;
    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userService.findByUsername(username);

        LOGGER.info("User found with username and password and email and roles: {} ",
                user.getUsername() + " " + " " + user.getEmail() + " " + Arrays.toString(user.getRoles().toArray()));
        return MyUserDetails.build(user);
    }
}
