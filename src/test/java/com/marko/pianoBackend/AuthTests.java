package com.marko.pianoBackend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.marko.pianoBackend.controller.QuestionController;
import com.marko.pianoBackend.dto.ListQuestions;
import com.marko.pianoBackend.service.StackOverflowService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(QuestionController.class)
class AuthTests {
  @MockBean
  StackOverflowService<ListQuestions> stackOverflowService;

  @Autowired
  WebTestClient webTestClient;
  @Test
  void testGetAddressList() throws JsonProcessingException {
    webTestClient.get()
            .uri("/search/test")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isUnauthorized();
  }
}
