package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.DeleteHistoryFactory.answer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {

  public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1,
    "Answers Contents1");
  public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1,
    "Answers Contents2");
  public static final Answer A3 = new Answer(UserTest.SANJIGI, QuestionTest.Q3,
    "Answers Contents3");

  @DisplayName("답변이 본인 답변이 아니면 삭제 할 수 없다.")
  @Test
  public void spec01() {
    assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI))
      .isInstanceOf(CannotDeleteException.class)
      .hasMessage("답변을 삭제할 권한이 없습니다.");
  }

  @DisplayName("본인 답변은 삭제할 수 있다.")
  @Test
  public void spec02() throws CannotDeleteException {
    final DeleteHistory deleteHistory = A1.delete(UserTest.JAVAJIGI);
    Assertions.assertAll(
      () -> assertThat(A1.isDeleted()).isTrue(),
      () -> assertThat(deleteHistory).isEqualTo(answer(A1))
    );
  }
}
