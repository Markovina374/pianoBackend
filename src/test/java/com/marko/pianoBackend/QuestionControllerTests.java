package com.marko.pianoBackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.marko.pianoBackend.controller.QuestionController;
import com.marko.pianoBackend.dto.ListQuestions;
import com.marko.pianoBackend.service.StackOverflowService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.marko.pianoBackend.TestHelper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@WebFluxTest(controllers = QuestionController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
class QuestionControllerTests {

  @Autowired
  WebTestClient webTestClient;
  @MockBean
  StackOverflowService<ListQuestions> stackOverflowService;

  @BeforeEach
  public void init() {
    given(stackOverflowService.getEntity("test"))
            .willReturn(Mono.just(TestHelper.resolveListQuestions()));
  }
  @Test
  void testController() {
    ListQuestions responseBody = webTestClient.get()
            .uri("/search/test")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody(ListQuestions.class)
            .returnResult()
            .getResponseBody();

    assertNotNull(responseBody);
    assertNotNull(responseBody.getItems());
    assertFalse(responseBody.getItems().isEmpty());
    assertEquals(1, responseBody.getItems().size());
    assertEquals(TITLE, responseBody.getItems().get(0).getTitle());
    assertEquals(LINK, responseBody.getItems().get(0).getLink());
    assertNotNull(responseBody.getItems().get(0).getOwner());
    assertEquals(OWNER_NAME, responseBody.getItems().get(0).getOwner().name());
  }

}
