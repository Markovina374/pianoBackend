package com.marko.pianoBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * A list of questions. Is the root element when searching for questions
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
public class ListQuestions implements StackOverflowRootEntity {
  List<Question> items;
}
