package com.netcracker.store.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UsersRole implements GrantedAuthority {
    ROLE_ADMIN, ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
