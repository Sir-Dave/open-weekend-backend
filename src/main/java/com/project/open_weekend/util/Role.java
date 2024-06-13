package com.project.open_weekend.util;

import static com.project.open_weekend.util.Authority.ADMIN_AUTHORITIES;
import static com.project.open_weekend.util.Authority.USER_AUTHORITIES;

public enum Role {
    ROLE_USER(USER_AUTHORITIES),
    ROLE_ADMIN(ADMIN_AUTHORITIES);

    private final String[] authorities;

    Role(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
