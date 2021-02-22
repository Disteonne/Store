package com.netcracker.store.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Credential implements GrantedAuthority {
    ADMIN, USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
