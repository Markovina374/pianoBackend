package com.marko.piano;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.marko.piano.dto.Question;
import com.marko.piano.dto.QuestionResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.marko.piano.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Integration test with Wire Mock
 */
@SpringBootTest
@AutoConfigureWebTestClient
class WireMockTest {
  private WireMockServer wireMockServer;
  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  WebTestClient webTestClient;

  @BeforeEach
  void init() {
    wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(7071));
    wireMockServer.start();
  }

  @AfterEach
  void close() {
    wireMockServer.stop();
  }

  @Test
  void testGetQuestionResponse() throws JsonProcessingException {
    QuestionResponse testQuestions = TestHelper.resolveListQuestions();

    wireMockServer.stubFor(WireMock.get(anyUrl())
            .withQueryParam("order", equalTo("desc"))
            .withQueryParam("sort", equalTo("activity"))
            .withQueryParam("intitle", equalTo("test"))
            .withQueryParam("site", equalTo("stackoverflow"))
            .willReturn(WireMock.okJson(objectMapper.writeValueAsString(testQuestions))));

    webTestClient.get()
            .uri("/search/test")
            .accept(MediaType.APPLICATION_NDJSON)
            .headers(httpHeaders -> httpHeaders.setBasicAuth("dXNlcjpwYXNzd29yZA=="))
            .exchange()
            .expectBodyList(Question.class)
            .consumeWith(list -> {
              assertNotNull(list);
              assertEquals(1, list.getResponseBody().size());
              assertEquals(TITLE, list.getResponseBody().get(0).getTitle());
              assertEquals(LINK, list.getResponseBody().get(0).getLink());
              assertNotNull(list.getResponseBody().get(0).getOwner());
              assertEquals(OWNER_NAME, list.getResponseBody().get(0).getOwner().name());
            });
  }
}
