package com.github.lilicaway.tiresias.web;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenServiceConfiguration {
  @Bean
  @Qualifier("open-weather-restTemplate")
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }
}
