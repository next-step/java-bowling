package qna.domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

public class AnswerTest {

  public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1,
      "Answers Contents1");
  public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1,
      "Answers Contents2");

  @Test
  @DisplayName("답변자인지 확인")
  public void isOwner() {
    assertThat(A1.isOwner(UserTest.JAVAJIGI)).isTrue();
  }

  @Test
  @DisplayName("answer를 deleteHistory로 변경 확인")
  public void turnAnswerIntoDeleteHistory() throws CannotDeleteException {
    DeleteHistory deleteHistory = new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(),
        LocalDateTime
            .now());
    assertThat(A1.turnAnswerIntoDeleteHistory(UserTest.JAVAJIGI)).isEqualTo(deleteHistory);
  }
}
