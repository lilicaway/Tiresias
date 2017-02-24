package com.github.lilicaway.tiresias.web;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.github.lilicaway.services.client.rest.httpUtils.HttpGenericHandler;
import com.github.lilicaway.tiresias.web.service.OpenWeatherService;

/**
 * Configuration that takes care of loading the beans from the controller package
 */
@Configuration
@ComponentScan
@EnableWebMvc
public class TestOpenWeatherServiceConfiguration {
  @Bean
  public HttpGenericHandler getHttpGenericHandler() {
    return new HttpGenericHandler();
  }

  @Bean
  @Primary
  public OpenWeatherService getOpenWeatherService() {
    return Mockito.mock(OpenWeatherService.class);
  }
}
