package com.hackaton.psd2.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.hackaton.psd2.rest.RSClient;
import com.hackaton.psd2.rest.RSResponse;

@RestController
public class AccountController {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  private final String ACCOUNTS_URL =
      "https://apisandbox.openbankproject.com/obp/v2.0.0/my/accounts";
  private final String TOKEN =
      "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyIiOiIifQ.vaXgQ27FGRfXsKrBbnHKskZDIwmeG8tV_2qeExRQxZQ";

  @RequestMapping(value = "/accounts", method = RequestMethod.GET)
  public Optional<JsonNode> getAccounts() throws Exception {

    try {
      RSClient rSC = new RSClient.Builder(ACCOUNTS_URL).method(HttpMethod.GET)
          .contentType(MediaType.APPLICATION_JSON).token(TOKEN).build();
      RSResponse rSR = rSC.send();
      HttpStatus statusCode = rSR.getStatus();
      if (statusCode == HttpStatus.OK || statusCode == HttpStatus.CREATED) {
        return rSR.getJson();
      } else {
        log.error("Error en la respuesta de la peticion [" + statusCode + "]");
        throw new Exception("Error en la respuesta de la peticion [" + statusCode + "]");
      }

    } catch (Exception ex) {
      throw new Exception(ex.toString());
    }
  }

}
