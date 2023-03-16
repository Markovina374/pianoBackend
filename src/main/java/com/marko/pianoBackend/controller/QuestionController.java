package com.marko.pianoBackend.controller;

import com.marko.pianoBackend.dto.ListQuestions;
import com.marko.pianoBackend.service.StackOverflowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Контроллер Аунтификации
 **/
@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class QuestionController {
  private final StackOverflowService<ListQuestions> questionService;

  @GetMapping("{text}")
  @Operation(summary = "Find by title", tags = "findQuestionsByTitle")
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "OK",
                  content = {@Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          schema = @Schema(implementation = ListQuestions.class))})})
  public Mono<ListQuestions> getQuestions(@PathVariable String text) {
    return questionService.getEntity(text);
  }
}