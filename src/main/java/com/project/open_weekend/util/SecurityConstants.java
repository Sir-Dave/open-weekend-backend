package com.project.open_weekend.util;

public class SecurityConstants {
    public static final int EXPIRATION_DATE = 602_000_000; // 5 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String JWT_ISSUER = "Open Weekend";
    public static final String AUTHORITIES = "authorities";
    public static final String AUDIENCE = "digital-lender-audience";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
    public static final String ACCESS_DENIED = "You do not have permission to access this page";
    public static final String[] PUBLIC_URLS = {"/api/v1/auth/**"};
}
