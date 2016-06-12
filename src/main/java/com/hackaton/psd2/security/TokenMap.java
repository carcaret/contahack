package com.hackaton.psd2.security;

public interface TokenMap {

    void setUserToken(String user, String token);

    String getUserToken(String user);

}
