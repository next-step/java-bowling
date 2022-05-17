package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {

  public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1,
      "Answers Contents1");
  public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1,
      "Answers Contents2");

  @Test
  @DisplayName("답변 삭제")
  void delete() {
    A1.delete();
    assertThat(A1.isDeleted()).isTrue();
  }

  @Test
  @DisplayName("질문자와 답변자가 다른 경우 답변 삭제 예외 처리")
  void delete_ShouldBeException() {
    assertThatThrownBy(A2::delete)
        .isInstanceOf(CannotDeleteException.class);
  }
}
