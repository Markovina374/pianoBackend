package com.marko.piano.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * A list of question. Is the root element when searching for questions
 * @author Markov Vlad
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
public class QuestionResponse {
  /**
   * List of question
   */
  List<Question> items;
}
