package com.marko.piano;

import com.marko.piano.dto.QuestionResponse;
import com.marko.piano.dto.Owner;
import com.marko.piano.dto.Question;
import com.marko.piano.dto.StackOverflowDate;

import java.util.List;

public class TestHelper {
  public final static String TITLE = "How to write tests on wire mock";
  public final static String LINK = "localhost:8080/api/v1/swagger-ui";
  public final static String OWNER_NAME = "Vlad Markov";
  public static QuestionResponse resolveListQuestions() {
    QuestionResponse questionResponse = new QuestionResponse();
    Question question = new Question();
    question.setAnswered(true);
    question.setDate(new StackOverflowDate(123123123L));
    question.setLink(LINK);
    question.setTitle(TITLE);
    question.setOwner(new Owner(OWNER_NAME));
    questionResponse.setItems(List.of(question));
    return questionResponse;
  }
}
