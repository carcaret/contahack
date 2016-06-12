package com.hackaton.psd2.security.impl;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenMapImpl {

    private static final Map<String, String> TOKENS = new ConcurrentHashMap<>();

    private final String TOKEN =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyIiOiIifQ.vaXgQ27FGRfXsKrBbnHKskZDIwmeG8tV_2qeExRQxZQ";

    public void setUserToken(String user, String token) {
        TOKENS.put(user, token);
    }

    public String getUserToken(String user) {
        return TOKENS.get(user);
        //return TOKEN;
    }

}
