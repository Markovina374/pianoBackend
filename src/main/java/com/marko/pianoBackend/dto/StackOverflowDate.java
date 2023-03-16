package com.marko.pianoBackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Custom date for parsing date from api
 */
@NoArgsConstructor
public class StackOverflowDate {
  @Getter
  private Date date;

  public StackOverflowDate(Long time) {
    this.date = new Date(time * 1000);
  }
}