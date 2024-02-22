package com.example.demo.models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    DIRECTOR,
    MANAGER,
    TEACHER,
    STUDENT,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
