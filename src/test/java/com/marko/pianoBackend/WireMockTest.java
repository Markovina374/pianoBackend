package com.marko.pianoBackend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.marko.pianoBackend.dto.ListQuestions;
import com.marko.pianoBackend.service.StackOverflowService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;

@SpringBootTest
//@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
class WireMockTest {
  private WireMockServer wireMockServer;
  @Autowired
  ObjectMapper objectMapper;
  @Autowired
  StackOverflowService<ListQuestions> stackOverflowService;

  @BeforeEach
  void setUp() {
    wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(7071));
    wireMockServer.start();
  }
  @AfterEach
  void tearDown() {
    wireMockServer.stop();
  }

  @Test
  void testGetAddressList() throws JsonProcessingException {
    ListQuestions testQuestions = TestHelper.resolveListQuestions();


    String jsonInString = objectMapper.writeValueAsString(testQuestions);
    wireMockServer.stubFor(WireMock.get(anyUrl())
            .withQueryParam("order", equalTo("desc"))
            .withQueryParam("sort", equalTo("activity"))
            .withQueryParam("intitle", equalTo("test"))
            .withQueryParam("site", equalTo("stackoverflow"))
            .willReturn(WireMock.okJson(objectMapper.writeValueAsString(testQuestions))));

    stackOverflowService.getEntity("test").blockOptional().get();
  }
}
