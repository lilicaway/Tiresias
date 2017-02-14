package com.github.lilicaway.services.client.rest.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.github.lilicaway.services.client.rest.dto.bussiness.MyEntity;

@Service
public class MyService {

  // Would be better to change this???
  // TODO We need a better configuration for these types of urls
  static final String BASE_URI = "http://localhost:8090/myservices/ui";

  @Autowired
  @Qualifier("my-service-client-restTemplate")
  public RestTemplate restTemplate;

  public ResponseEntity<MyEntity> getStepBasedOnProcessForATask(String parameter,
      HttpEntity<Void> requestEntity) throws HttpStatusCodeException {
    Map<String, String> urlVariables = new HashMap<String, String>();

    String url = BASE_URI + "/{parameter}";
    urlVariables.put("parameter", parameter);
    // You add here all possible parameters for the rest call

    ResponseEntity<MyEntity> responseEntity =
        restTemplate.exchange(url, HttpMethod.GET, requestEntity, MyEntity.class, urlVariables);

    return responseEntity;
  }

}
