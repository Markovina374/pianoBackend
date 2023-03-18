package com.marko.piano.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Web client configuration
 * @author Markov Vlad
 */
@Configuration
public class WebClientConfig {
  /**
   * Path to api
   */
  @Value("${api.path}")
  private String apiPath;
  /**
   * Api version
   */
  @Value("${api.version}")
  private String version;

  /**
   * Return web client for work on stackoverflow
   * @return Configured WebClient
   */
  @Bean
  public WebClient webClient() {
    return WebClient.create(apiPath.concat(version));
  }
}
