package qna.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeleteHistoryTest {
  public static final DeleteHistory DA1 = new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), AnswerTest.A1.getWriter(), LocalDateTime.now());
  public static final DeleteHistory DA2 = new DeleteHistory(ContentType.ANSWER, AnswerTest.A2.getId(), AnswerTest.A2.getWriter(), LocalDateTime.now());
  public static final DeleteHistory DQ1 = new DeleteHistory(ContentType.QUESTION, QuestionTest.Q1.getId(), QuestionTest.Q1.getWriter(), LocalDateTime.now());
  public static final DeleteHistory DQ2 = new DeleteHistory(ContentType.QUESTION, QuestionTest.Q2.getId(), QuestionTest.Q2.getWriter(), LocalDateTime.now());

  @Test
  @DisplayName("DeleteHistories에 Answer를 전달하여 해당 타입의 DeleteHistory를 만들 수 있다")
  public void create_answerType_DeleteHistory() throws Exception {
    //given
    //when
    //then
    assertDoesNotThrow(() -> DeleteHistory.create(AnswerTest.A1));
  }

  @Test
  @DisplayName("DeleteHistories에 Question을 전달하여 해당 타입의 DeleteHistory를 만들 수 있다")
  public void create_questionType_DeleteHistory() throws Exception {
    //given
    //when
    //then
    assertDoesNotThrow(() -> DeleteHistory.create(QuestionTest.Q1));
  }
}
