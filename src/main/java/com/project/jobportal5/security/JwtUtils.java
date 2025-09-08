package com.project.jobportal5.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jobportal.app.jwtSecret:jobPortalSecretKey}")
    private String jwtSecret;

    @Value("${jobportal.app.jwtExpirationMs:86400000}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        // JWT implementation can be added later when JWT dependencies are included
        return "dummy-token";
    }

    public String getUserNameFromJwtToken(String token) {
        // JWT implementation can be added later
        return "dummy-user";
    }

    public boolean validateJwtToken(String authToken) {
        // JWT implementation can be added later
        return false;
    }
}