package com.hackaton.psd2.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ExceptionController {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @ExceptionHandler(Exception.class)
  public JsonNode handleException(Exception e) throws IOException {
    return MAPPER.readTree(
        String.format("\"error\": \"Se produjo un error en el proceso - %s\"", e.getMessage()));
  }


}
