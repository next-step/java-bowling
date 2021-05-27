package qna.domain;

import java.time.LocalDateTime;

public class DeleteHistoryTest {
  public static final DeleteHistory DA1 = new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), AnswerTest.A1.getWriter(), LocalDateTime.now());
  public static final DeleteHistory DA2 = new DeleteHistory(ContentType.ANSWER, AnswerTest.A2.getId(), AnswerTest.A2.getWriter(), LocalDateTime.now());
  public static final DeleteHistory DQ1 = new DeleteHistory(ContentType.QUESTION, QuestionTest.Q1.getId(), QuestionTest.Q1.getWriter(), LocalDateTime.now());
  public static final DeleteHistory DQ2 = new DeleteHistory(ContentType.QUESTION, QuestionTest.Q2.getId(), QuestionTest.Q2.getWriter(), LocalDateTime.now());
}
