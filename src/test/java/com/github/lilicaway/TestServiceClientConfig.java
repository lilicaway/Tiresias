package com.github.lilicaway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.github.lilicaway.services.client.rest.httpUtils.HttpGenericHandler;

@Configuration
public class TestServiceClientConfig {

  @Bean
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }

  @Bean
  public HttpGenericHandler getHttpGenericHandler() {
    return new HttpGenericHandler();
  }
}
