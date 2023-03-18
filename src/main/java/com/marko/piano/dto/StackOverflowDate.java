package com.marko.piano.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Custom date for parsing date from api
 *
 * @author Markov Vlad
 */
@NoArgsConstructor
public class StackOverflowDate {
  /**
   * Date of question
   */
  @Getter
  private LocalDateTime date;

  public StackOverflowDate(Long time) {
    this.date = LocalDateTime.ofInstant(Instant.ofEpochMilli(time * 1000), ZoneId.systemDefault());
  }
}