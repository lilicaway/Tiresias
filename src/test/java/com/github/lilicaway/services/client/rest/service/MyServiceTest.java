package com.github.lilicaway.services.client.rest.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.github.lilicaway.services.client.rest.dto.bussiness.MyEntity;

@RunWith(MockitoJUnitRunner.class)
public class MyServiceTest {

  private MyService service;
  private RestTemplate mockRestTemplate;

  @Before
  public void setUp() {
    service = new MyService();
    mockRestTemplate = mock(RestTemplate.class);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testGetStepBasedOnProcessForATask() {
    String parameter = "aParameter";
    service.restTemplate = mockRestTemplate;
    service.getStepBasedOnProcessForATask(parameter, new HttpEntity<Void>() {});

    verify(mockRestTemplate, Mockito.atLeastOnce()).exchange(
        eq("http://localhost:8090/myservices/ui/{parameter}"), eq(HttpMethod.GET),
        any(HttpEntity.class), same(MyEntity.class), any(Map.class));
  }

}
