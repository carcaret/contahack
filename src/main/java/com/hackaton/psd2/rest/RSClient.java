package com.hackaton.psd2.rest;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RSClient {

  private final String uri;
  private final HttpMethod method;
  private final MediaType contentType;
  private final String token;
  private final HttpHeaders headers;
  private final JsonNode body;

  public RSClient(String uri, HttpMethod method, MediaType contentType, String token,
      HttpHeaders headers, JsonNode body) {
    this.uri = uri;
    this.method = method;
    this.contentType = contentType;
    this.token = token;
    this.headers = headers;
    this.body = body;
  }

  public RSResponse send() {
    RestTemplate restTemplate = new RestTemplate();
    if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
      headers.add("Authorization", String.format("DirectLogin token=\"%s\"", token));
    }
    if (!headers.containsKey(HttpHeaders.CONTENT_TYPE)) {
      headers.setContentType(contentType);
    }
    HttpEntity<String> entity;
    if (body != null) {
      entity = new HttpEntity<>(body.toString(), headers);
    } else {
      entity = new HttpEntity<>(headers);
    }
    ResponseEntity<JsonNode> response = restTemplate.exchange(uri, method, entity, JsonNode.class);
    if (response.hasBody()) {
      return new RSResponse(response.getStatusCode(), response.getBody());
    } else {
      return new RSResponse(response.getStatusCode());
    }
  }

  public static class Builder {

    private String uri;
    private HttpMethod method;
    private MediaType contentType;
    private String token;
    private HttpHeaders headers = new HttpHeaders();
    private JsonNode body;

    public Builder(String uri) {
      this.uri = uri;
    }

    public Builder contentType(MediaType mediaType) {
      this.contentType = mediaType;
      return this;
    }

    public Builder method(HttpMethod method) {
      this.method = method;
      return this;
    }

    public Builder token(String token) {
      this.token = token;
      return this;
    }

    public Builder header(String name, String value) {
      headers.add(name, value);
      return this;
    }

    public Builder body(JsonNode body) {
      this.body = body;
      return this;
    }

    public RSClient build() {
      return new RSClient(uri, method, contentType, token, headers, body);
    }

  }

}
