package com.project.open_weekend.auth;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


@Service
public class LoginAttemptService {
    private static final int  MAXIMUM_LOGIN_ATTEMPT = 5;
    private  static final int ATTEMPT_INCREMENT = 1;

    private static final LoadingCache<String, Integer> loginAttemptCache = CacheBuilder.newBuilder()
            .expireAfterWrite(15, TimeUnit.MINUTES)
            .maximumSize(100)
            .build(new CacheLoader<>() {
                @Override
                public Integer load(String key) {
                    return 0;
                }
            });

    public void evictUserFromLoginAttemptCache(String username){
        loginAttemptCache.invalidate(username);
    }

    public void addUserToLoginAttemptCache(String username){
        var attempts = 0;
        try {
            attempts = loginAttemptCache.get(username) + ATTEMPT_INCREMENT;
        }
        catch (ExecutionException exception){
            exception.printStackTrace();
        }
        loginAttemptCache.put(username, attempts);
    }

    public boolean hasExceededMaximumAttempt(String username){
        try {
            return loginAttemptCache.get(username) >= MAXIMUM_LOGIN_ATTEMPT;
        }
        catch (ExecutionException ex){
            ex.printStackTrace();
        }
        return false;
    }
}
