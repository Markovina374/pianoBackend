package com.marko.piano.service;

import com.marko.piano.dto.Question;
import com.marko.piano.dto.QuestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * Service for receiving information on questions
 */
@Service
@RequiredArgsConstructor
public class QuestionService implements StackOverflowService<Question> {

  /**
   * Web client
   */
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
   * Default values
   */
  private static final String DEFAULT_ORDER_VALUE = "desc";
  private static final String DEFAULT_SORT_VALUE = "activity";
  private static final String DEFAULT_SITE_VALUE = "stackoverflow";

  /**
   * The method looks for a list of questions by title
   * @author Markov Vlad
   */
  @Override
  public Flux<Question> getEntities(String title) {
    return webClient
            .get()
            .uri(URI, builder -> builder
                    .queryParam(PARAM_ORDER, DEFAULT_ORDER_VALUE)
                    .queryParam(PARAM_SORT, DEFAULT_SORT_VALUE)
                    .queryParam(PARAM_TITLE, title)
                    .queryParam(PARAM_SITE, DEFAULT_SITE_VALUE)
                    .build())
            .retrieve()
            .bodyToMono(QuestionResponse.class)
            .flatMapIterable(QuestionResponse::getItems);
  }
}
