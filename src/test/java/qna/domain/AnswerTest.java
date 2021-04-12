package qna.domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

public class AnswerTest {

  public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1,
      "Answers Contents1");
  public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1,
      "Answers Contents2");

  @Test
  @DisplayName("삭제 확인")
  public void isDeleted() {
    A1.delete();
    assertThat(A1.isDeleted()).isTrue();
  }

  @Test
  @DisplayName("답변자인지 확인")
  public void isOwner() {
    assertThat(A1.isOwner(UserTest.JAVAJIGI)).isTrue();
  }

  @Test
  @DisplayName("다른 사람이 쓴 답변이 있을 경우 삭제 불가")
  public void validateDelete() {
    assertThatThrownBy(() -> {
      A1.validateDelete(UserTest.SANJIGI);
    }).isInstanceOf(CannotDeleteException.class);
  }

  @Test
  @DisplayName("answer를 deleteHistory로 변경 확인")
  public void turnAnswerIntoDeleteHistory() throws CannotDeleteException {
        DeleteHistory deleteHistory = DeleteHistory.of(A1);
        assertThat(A1.turnAnswerIntoDeleteHistory(UserTest.JAVAJIGI)).isEqualTo(deleteHistory);
  }
}
