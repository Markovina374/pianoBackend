package com.marko.pianoBackend;

import com.marko.pianoBackend.dto.ListQuestions;
import com.marko.pianoBackend.dto.Owner;
import com.marko.pianoBackend.dto.Question;
import com.marko.pianoBackend.dto.StackOverflowDate;

import java.util.List;

public class TestHelper {
  public final static String TITLE = "How to write tests on wire mock";
  public final static String LINK = "localhost:8080/api/v1/swagger-ui";
  public final static String OWNER_NAME = "Vlad Markov";
  public static ListQuestions resolveListQuestions() {
    ListQuestions testQuestions = new ListQuestions();
    Question question = new Question();
    question.setAnswered(true);
    question.setDate(new StackOverflowDate(123123123L));
    question.setLink(LINK);
    question.setTitle(TITLE);
    question.setOwner(new Owner(OWNER_NAME));
    testQuestions.setItems(List.of(question));
    return testQuestions;
  }
}
