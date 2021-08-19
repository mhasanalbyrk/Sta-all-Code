package com.obss.jss.onlinemarketplace.service;

import com.obss.jss.onlinemarketplace.model.Role;

public interface RoleService {
    Role findByName(String user);
}
