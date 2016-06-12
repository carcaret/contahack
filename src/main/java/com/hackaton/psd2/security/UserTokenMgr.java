package com.hackaton.psd2.security;

import javax.security.auth.login.LoginException;

public interface UserTokenMgr {

    String getUserToken(String user, String password) throws LoginException;

}
