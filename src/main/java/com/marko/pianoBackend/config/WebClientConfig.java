package com.marko.pianoBackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Web client configuration
 */
@Configuration
public class WebClientConfig {
  @Value("${apiPath}")
  private String apiPath;
  @Bean
  public WebClient webClient() {
    return WebClient.create(apiPath);
  }
}
