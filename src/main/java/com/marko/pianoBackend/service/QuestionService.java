package com.marko.pianoBackend.service;

import com.marko.pianoBackend.dto.ListQuestions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Service for receiving information on questions
 */
@Service
@RequiredArgsConstructor
public class QuestionService implements StackOverflowService<ListQuestions> {

  private final WebClient webClient;

  /**
   * Additional uri
   */
  private static final String URI = "/search";
  /**
   * Name parameters
   */
  private static final String PARAM_ORDER = "order";
  private static final String PARAM_SORT = "sort";
  private static final String PARAM_TITLE = "intitle";
  private static final String PARAM_SITE = "site";

  /**
   * The method looks for a list of questions by title
   */
  public Mono<ListQuestions> getEntity(String title) {
    return webClient
            .get()
            .uri(VERSION.concat(URI), builder -> builder
                    .queryParam(PARAM_ORDER, "desc")
                    .queryParam(PARAM_SORT, "activity")
                    .queryParam(PARAM_TITLE, title)
                    .queryParam(PARAM_SITE, "stackoverflow")
                    .build())
            .retrieve()
            .bodyToMono(ListQuestions.class);
  }
}
