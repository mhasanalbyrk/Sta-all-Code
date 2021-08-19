package com.obss.jss.onlinemarketplace.service.impl;

import com.obss.jss.onlinemarketplace.model.Role;
import com.obss.jss.onlinemarketplace.repository.RoleRepository;
import com.obss.jss.onlinemarketplace.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j

@Service
public class RoleServiceImpl implements RoleService {
    public static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        Objects.requireNonNull(name, "role name cannot be null");
        LOGGER.info("Role fond by name: {} ", roleRepository.findByName(name));
        return roleRepository.findByName(name);//.orElseThrow(RoleNotFoundException::new);
    }
}
