package com.hackaton.psd2.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.hackaton.psd2.rest.RSClient;
import com.hackaton.psd2.rest.RSResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import javax.ws.rs.core.MediaType;

@RestController
public class AccountController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final String ACCOUNTS_URL = "https://apisandbox.openbankproject.com/obp/v2.0.0/my/accounts";
    private final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyIiOiIifQ.vaXgQ27FGRfXsKrBbnHKskZDIwmeG8tV_2qeExRQxZQ";

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public Optional<JsonNode> getAccounts() throws Exception {

        try {
            RSClient rSC = new RSClient.Builder(ACCOUNTS_URL).type(MediaType.APPLICATION_JSON_TYPE).token(TOKEN).build();
            RSResponse rSR = rSC.get();
            int statusCode = rSR.getStatus();
            if (statusCode == 200 || statusCode == 201) {
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
