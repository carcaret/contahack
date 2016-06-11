package com.hackaton.psd2.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.hackaton.psd2.helper.URIs;
import com.hackaton.psd2.rest.RSClient;
import com.hackaton.psd2.rest.RSResponse;
import com.hackaton.psd2.security.TokenMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AccountController {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private TokenMap tokenMap;

  @RequestMapping(value = "/accounts", method = RequestMethod.GET)
  public JsonNode getAccounts(Principal principal) throws Exception {

    try {
      RSClient rSC = new RSClient.Builder(URIs.MY_ACCOUNTS).method(HttpMethod.GET)
          .contentType(MediaType.APPLICATION_JSON).token(tokenMap.getUserToken(""))
          .build();
      RSResponse rSR = rSC.send();
      HttpStatus statusCode = rSR.getStatus();
      if (statusCode == HttpStatus.OK || statusCode == HttpStatus.CREATED) {
        return rSR.getJson().get();
      } else {
        log.error("Error en la respuesta de la peticion [" + statusCode + "]");
        throw new Exception("Error en la respuesta de la peticion [" + statusCode + "]");
      }

    } catch (Exception ex) {
      throw new Exception(ex.toString());
    }
  }

}
