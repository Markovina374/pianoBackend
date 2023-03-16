package com.marko.pianoBackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Information of the question
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Question {
  private Owner owner;
  private String title;
  private String link;
  @JsonProperty("is_answered")
  private boolean isAnswered;
  @JsonProperty("creation_date")
  private StackOverflowDate date;
}
