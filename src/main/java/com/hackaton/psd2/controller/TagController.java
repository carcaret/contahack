package com.hackaton.psd2.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackaton.psd2.helper.URIs;
import com.hackaton.psd2.rest.RSClient;
import com.hackaton.psd2.rest.RSResponse;
import com.hackaton.psd2.security.impl.TokenMapImpl;

@RestController
@RequestMapping(value = "/tags")
public class TagController {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Autowired
  private TokenMapImpl tokenMap;

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public JsonNode getTags(Principal principal) {
    try {
      RSClient client = new RSClient.Builder(URIs.TAGS).contentType(MediaType.APPLICATION_JSON)
          .token(tokenMap.getUserToken("")).method(HttpMethod.GET).build();
      RSResponse response = client.send();
      if (response.getStatus() == HttpStatus.OK) {
        return response.getJson().get();
      } else {
        return MAPPER
            .readTree("{\"error\": \"No se pudo recuperar los tags de la cuenta indicada\"}");
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public JsonNode addTag(@RequestBody JsonNode json) {
    try {
      RSClient client = new RSClient.Builder(URIs.TAGS).contentType(MediaType.APPLICATION_JSON)
          .token(tokenMap.getUserToken("")).method(HttpMethod.POST).body(json).build();
      RSResponse response = client.send();
      if (response.getStatus() == HttpStatus.OK) {
        return response.getJson().get();
      } else {
        return MAPPER
            .readTree("{\"error\": \"No se pudo recuperar los tags de la cuenta indicada\"}");
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
