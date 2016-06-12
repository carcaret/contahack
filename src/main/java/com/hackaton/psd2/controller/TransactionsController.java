package com.hackaton.psd2.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.hackaton.psd2.helper.URIs;
import com.hackaton.psd2.rest.RSClient;
import com.hackaton.psd2.rest.RSResponse;
import com.hackaton.psd2.security.impl.TokenMapImpl;

@RestController
public class TransactionsController {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private TokenMapImpl tokenMap;

  private final String BANK_ID = "rbs";
  private final String ACCOUNT_ID = "5dHBvPFLLbnnBi2fOYOy";
  private final String VIEW_ID = "owner";

  @RequestMapping(value = "/transactions", method = RequestMethod.GET)
  public Optional<JsonNode> getTransactions() throws Exception {

    try {
      RSClient rSC = new RSClient.Builder(String.format(URIs.TRANSACTION_URL, BANK_ID, ACCOUNT_ID, VIEW_ID)).method(HttpMethod.GET)
          .contentType(MediaType.APPLICATION_JSON).token(tokenMap.getUserToken("")).build();
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
