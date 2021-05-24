package qna.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswersTest {
  public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
  public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

  @Test
  @DisplayName("다수의 Answer를 담은 후에 각각의 Answer Entity를 비교할 수 있다.")
  public void save_many_answer_and_compare() throws Exception {
      //given
      Answers answers = new Answers();

      //when
      answers.add(A1);
      answers.add(A2);

      //then
      List<Answer> answerList = answers.of();
      assertAll(
          () -> assertEquals(answerList.get(0), A1),
          () -> assertEquals(answerList.get(1), A2)
      );
  }
}