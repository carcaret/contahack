package com.hackaton.psd2.security;

import javax.security.auth.login.LoginException;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpStatusCodeException;

import com.fasterxml.jackson.databind.JsonNode;
import com.hackaton.psd2.rest.RSClient;
import com.hackaton.psd2.rest.RSResponse;

public class UserTokenMgr {

  final private static String LOGIN_URL = "https://apisandbox.openbankproject.com/my/logins/direct";
  final private static String MY_API_KEY = "c423yqe1jrttsenrp1sqdbuisb5zixze33ogoom5";

  public static String getUserToken(String user, String password) throws LoginException {
    String authStr = "DirectLogin username=\"" + user + "\", password=\"" + password
        + "\", consumer_key=\"" + MY_API_KEY + "\"";

    try {
      RSClient client = new RSClient.Builder(LOGIN_URL).method(HttpMethod.POST)
          .contentType(MediaType.APPLICATION_JSON).header("Authorization", authStr).build();
      RSResponse response = client.send();
      JsonNode body = response.getJson().get();
      if (body.has("token")) {
        return body.get("token").asText();
      } else {
        throw new LoginException("body received without token: " + body.toString());
      }

    } catch (HttpStatusCodeException e) {
      throw new LoginException(e.getResponseBodyAsString());
    }
  }

}
