package com.github.lilicaway.services.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ServiceClientConfig {
  @Bean
  @Qualifier("my-service-client-restTemplate")
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }
}
