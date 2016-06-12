package com.hackaton.psd2.rest;

import com.hackaton.psd2.security.impl.UserTokenMgrImpl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.security.auth.login.LoginException;

public class RSClientTest {

  @Test
  public void successGetCall() throws LoginException {
    RSClient client =
        new RSClient.Builder("https://apisandbox.openbankproject.com/obp/v2.0.0/users/current")
            .contentType(MediaType.APPLICATION_JSON).method(HttpMethod.GET)
            .token(new UserTokenMgrImpl().getUserToken("alberto.gonzalez.perez.x.x@example.com", "b95eb3"))
            .build();
    RSResponse response = client.send();
    Assert.assertEquals(HttpStatus.OK, response.getStatus());
    Assert.assertTrue(response.getJson().isPresent());
    System.out.println(response.getJson().toString());
  }



}
