package com.hackaton.psd2.rest;

import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Test;

public class RSClientTest {

  @Test
  public void successGetCall() {
    RSClient client =
        new RSClient.Builder("https://apisandbox.openbankproject.com/obp/v2.0.0/accounts")
            .accept(MediaType.APPLICATION_JSON_TYPE)
            .token(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyIiOiIifQ.vaXgQ27FGRfXsKrBbnHKskZDIwmeG8tV_2qeExRQxZQ")
            .build();
    RSResponse response = client.get();
    Assert.assertEquals(200, response.getStatus());
    Assert.assertTrue(response.getJson().isPresent());
  }

  @Test
  public void successPostCall() {
    RSClient client = new RSClient.Builder(
        "https://apisandbox.openbankproject.com/obp/v2.0.0/banks/at03-bank-x/accounts/1fc1d0a8-25ff-4fee-a1d0-cbc8dfe64601/accountant/transactions/6f668296-b5ef-4980-888d-8da28cb274e9/metadata/tags")
            .accept(MediaType.APPLICATION_JSON_TYPE)
            .token(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyIiOiIifQ.vaXgQ27FGRfXsKrBbnHKskZDIwmeG8tV_2qeExRQxZQ")
            .build();
    RSResponse response = client.post("{\"value\":\"testTat\"}");
    Assert.assertEquals(200, response.getStatus());
    Assert.assertTrue(response.getJson().isPresent());
  }

}
