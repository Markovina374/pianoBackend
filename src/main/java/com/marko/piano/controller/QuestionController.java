package com.marko.piano.controller;

import com.marko.piano.dto.Question;
import com.marko.piano.service.StackOverflowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.http.MediaType.*;

/**
 * Controller for search question
 * @author Markov Vlad
 */
@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class QuestionController {
  /**
   * Service for questions
   */
  private final StackOverflowService<Question> questionService;

  /**
   * Search question by title
   * @param title title of question
   * @return questions
   */
  @GetMapping(value = "{title}", produces = APPLICATION_NDJSON_VALUE)
  @Operation(summary = "Find by title", tags = "findQuestionsByTitle")
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "OK",
                  content = {@Content(
                          mediaType = APPLICATION_NDJSON_VALUE,
                          schema = @Schema(implementation = Question.class))})})
  public Flux<Question> getQuestions(@PathVariable String title) {
    return questionService.getEntities(title);
  }
}