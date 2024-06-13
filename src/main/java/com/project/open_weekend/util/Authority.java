package com.project.open_weekend.util;

public class Authority {
    public static final String[] USER_AUTHORITIES = {"create-account"};
    public static final String[] ADMIN_AUTHORITIES = {
            "create-account",
            "event-moderation"
    };
}
