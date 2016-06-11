package com.hackaton.psd2.rest;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.http.HttpStatus;

import java.util.Optional;

public class RSResponse {

  private final HttpStatus status;
  private Optional<JsonNode> json;

  public RSResponse(HttpStatus status) {
    this.status = status;
  }

  public RSResponse(HttpStatus status, JsonNode json) {
    this.status = status;
    this.json = Optional.of(json);
  }

  public HttpStatus getStatus() {
    return status;
  }

  public Optional<JsonNode> getJson() {
    return json;
  }
}
