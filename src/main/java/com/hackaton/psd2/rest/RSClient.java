package com.hackaton.psd2.rest;

import java.io.IOException;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RSClient {

  private static final Client CLIENT = Client.create();
  private static final ObjectMapper MAPPER = new ObjectMapper();

  private final WebResource webResource;

  private RSClient(WebResource webResource) {
    this.webResource = webResource;
  }

  public RSResponse get() {
    try {
      return buildResponse(webResource.get(ClientResponse.class));
    } catch (Exception e) {
      throw new RuntimeException(
          String.format("Error while reading the response from %s", webResource.getURI()), e);
    }
  }

  public RSResponse post(String request) {
    try {
      return buildResponse(webResource.post(ClientResponse.class, request));
    } catch (Exception e) {
      throw new RuntimeException(
              String.format("Error while reading the response from %s", webResource.getURI()), e);
    }
  }

  private RSResponse buildResponse(ClientResponse response) throws IOException {
    if (response.hasEntity()) {
      return new RSResponse(response.getStatus(), MAPPER.readTree(response.getEntity(String.class)));
    } else {
      return new RSResponse(response.getStatus());
    }
  }


  public static class Builder {

    private WebResource webResource;

    public Builder(String resource) {
      webResource = CLIENT.resource(resource);
    }

    public Builder accept(MediaType mediaType) {
      webResource.accept(mediaType);
      return this;
    }

    public Builder token(String token) {
      webResource.header("Authorization", String.format("DirectLogin token=\"%s\"", token));
      return this;
    }

    public RSClient build() {
      return new RSClient(this.webResource);
    }

  }
}
