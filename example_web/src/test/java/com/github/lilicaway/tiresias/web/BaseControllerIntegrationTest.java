package com.github.lilicaway.tiresias.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.github.lilicaway.services.client.rest.httpUtils.HttpGenericHandler;
import com.github.lilicaway.tiresias.web.dto.ResponseResult;
import com.github.lilicaway.tiresias.web.service.OpenWeatherService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestOpenWeatherServiceConfiguration.class})
@WebAppConfiguration
public class BaseControllerIntegrationTest {
  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext ctx;

  @Mock
  private RestTemplate rt;

  @Mock
  HttpGenericHandler handler;

  @Autowired
  OpenWeatherService service;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
  }

  @Test
  public void testGetWeatherForACity() throws Exception {
    String aCity = "Madrid";

    ResponseResult body = new ResponseResult();
    body.setBase("someBase");
    ResponseEntity<ResponseResult> responseEntity =
        new ResponseEntity<ResponseResult>(body, HttpStatus.OK);

    Mockito.when(service.getOpenWeatherResponse(Mockito.eq(aCity), Mockito.any()))
        .thenReturn(responseEntity);

    mockMvc.perform(get("/weather/{aCity}", aCity)).andExpect(status().isOk())
        .andExpect(jsonPath("$.base").value("someBase"));

  }
}
