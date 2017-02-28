package com.github.lilicaway.tiresias.web.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.github.lilicaway.tiresias.web.dto.ResponseResult;

@RunWith(MockitoJUnitRunner.class)
public class OpenWeatherServiceTest {

  private OpenWeatherService service;
  private RestTemplate mockRestTemplate;

  @Before
  public void setUp() {
    service = new OpenWeatherService();
    mockRestTemplate = mock(RestTemplate.class);
  }

  @Test
  public void testGetWeatherForACity() throws URISyntaxException {
    String aCityName = "Madrid";
    service.restTemplate = mockRestTemplate;
    service.getOpenWeatherResponse(aCityName, new HttpEntity<Void>() {});

    URI uri = new URI(
        "http://api.openweathermap.org/data/2.5/weather?q=Madrid&APPID=d151aa923895fb4445fca9f9305e66ba");

    verify(mockRestTemplate, Mockito.atLeastOnce()).exchange(
        eq(uri),
        eq(HttpMethod.GET),
        any(HttpEntity.class), same(ResponseResult.class));
  }
}
