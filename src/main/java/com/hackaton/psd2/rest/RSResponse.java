package com.hackaton.psd2.rest;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Optional;

public class RSResponse {

  private final int status;
  private Optional<JsonNode> json;

  public RSResponse(int status) {
    this.status = status;
  }

  public RSResponse(int status, JsonNode json) {
    this.status = status;
    this.json = Optional.of(json);
  }

  public int getStatus() {
    return status;
  }

  public Optional<JsonNode> getJson() {
    return json;
  }
}
