package com.hackaton.psd2.security;

import javax.security.auth.login.LoginException;

import org.apache.commons.collections.map.MultiValueMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

public class UserTokenMgr {
	
	final private static String LOGIN_URL = "https://apisandbox.openbankproject.com/my/logins/direct";
	final private static String MY_API_KEY = "c423yqe1jrttsenrp1sqdbuisb5zixze33ogoom5";

	public static String getUserToken( String user, String password ) throws LoginException {
		String authStr = "DirectLogin username=\""
				+ user
				+ "\", password=\""
				+ password
				+ "\", consumer_key=\""
				+ MY_API_KEY
				+ "\""
				;
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authStr);
		headers.setContentType(MediaType.APPLICATION_JSON);      
		
		RestTemplate restTemplate = new RestTemplate();
		try {
			ResponseEntity<JsonNode> answer = restTemplate.exchange(LOGIN_URL, HttpMethod.POST, new HttpEntity<byte[]>(headers), JsonNode.class);
			JsonNode body = answer.getBody();
			if (body.has("token")) {
				return body.get("token").asText();
			}
			else {
				throw new LoginException("body received without token: "+body.toString());
			}
			
		} catch (HttpStatusCodeException e) {
			throw new LoginException(e.getResponseBodyAsString());
		}
	}
	
}
