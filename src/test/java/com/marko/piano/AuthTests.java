package com.marko.piano;

import com.marko.piano.dto.Question;
import com.marko.piano.service.StackOverflowService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class AuthTests {
  @MockBean
  StackOverflowService<Question> stackOverflowService;

  @Autowired
  WebTestClient webTestClient;
  @Test
  void testUnauthorized() {
    webTestClient.get()
            .uri("/search/test")
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus().isUnauthorized();
  }

  @Test
  void testAuthorized() {
    webTestClient.get()
            .uri("/search/test")
            .accept(MediaType.APPLICATION_NDJSON)
            .headers(httpHeaders -> httpHeaders.setBasicAuth("dXNlcjpwYXNzd29yZA=="))
            .exchange()
            .expectStatus().isOk();
  }
}
