package com.marko.piano.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Basic "in memory" security config
 * @author Markov Vlad
 */
@Configuration
//@EnableWebFluxSecurity
public class SecurityConfig {
  /**
   * Implementation of ReactiveUserDetailsService
   * @return MapReactiveUserDetailsService
   */
  @Bean
  public MapReactiveUserDetailsService userDetailsService() {
    UserDetails user = User.builder()
            .username("user")
            .password("{noop}password")
            .roles("USER")
            .build();
    return new MapReactiveUserDetailsService(user);
  }

  /**
   * Defines a filter chain which is capable of being matched against
   * a ServerWebExchange in order to decide whether it applies to that request.
   */
  @Bean
  public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
    return http
            .csrf().disable()
            .authorizeExchange()
            .pathMatchers("/").permitAll()
            .anyExchange().authenticated()
            .and()
            .httpBasic()
            .and()
            .formLogin().disable()
            .build();
  }
}


