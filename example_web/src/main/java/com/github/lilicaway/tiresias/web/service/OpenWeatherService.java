package com.github.lilicaway.tiresias.web.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.lilicaway.tiresias.web.dto.ResponseResult;

@Service
public class OpenWeatherService {

  public static final String BASE_URI =
      "http://api.openweathermap.org/data/2.5/weather";
  public static final String APP_ID = "d151aa923895fb4445fca9f9305e66ba";

  @Autowired
  @Qualifier("open-weather-restTemplate")
  public RestTemplate restTemplate;

  public ResponseEntity<ResponseResult> getOpenWeatherResponse(String cityName,
      HttpEntity<Void> requestEntity) throws HttpStatusCodeException {

    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URI)
        .queryParam("q", cityName).queryParam("APPID", APP_ID);

    URI uri = builder.build().encode().toUri();
    System.out.println("uri:" + uri);
    ResponseEntity<ResponseResult> responseEntity = restTemplate.exchange(
        uri, HttpMethod.GET, requestEntity, ResponseResult.class);

    return responseEntity;
  }
}
