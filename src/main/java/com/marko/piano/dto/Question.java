package com.marko.piano.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Information of the question implements StackOverflowMainEntity
 * @author Markov Vlad
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Question implements StackOverflowMainEntity{
  /**
   * Owner of question
   */
  private Owner owner;
  /**
   * Title
   */
  private String title;
  /**
   * Link to question
   */
  private String link;
  /**
   * Is answered
   */
  @JsonProperty("is_answered")
  private boolean isAnswered;
  /**
   * Question date
   */
  @JsonProperty("creation_date")
  private StackOverflowDate date;
}
