package com.hackaton.psd2.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.hackaton.psd2.ContahackApplication;
import com.hackaton.psd2.security.TokenMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ContahackApplication.class)
public class AccountControllerTest {

  @InjectMocks
  private AccountController controller;

  @Mock
  private TokenMap tokenMap;

  @Test
  public void getAccountsTest() throws Exception {
    Mockito.when(tokenMap.getUserToken(Mockito.anyString())).thenReturn(
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyIiOiIifQ.vaXgQ27FGRfXsKrBbnHKskZDIwmeG8tV_2qeExRQxZQ");

    JsonNode json = controller.getAccounts(null);
    System.out.println(json.asText());
  }

}
