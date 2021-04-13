package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {

  public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1,"Answers Contents1");
  public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

  @Test
  @DisplayName("[Answer] 다른 사람이 쓴 답변이 있는 경우 예외 발생")
  void has_other_answer() {
    assertThatThrownBy(() ->
        A1.hasOthers(UserTest.SANJIGI)
    ).isInstanceOf(CannotDeleteException.class);
  }

  @Test
  @DisplayName("[Answer] 답변 작성자 일치 여부 테스트")
  void is_owner_test() {
    assertThat(A1.isOwner(UserTest.JAVAJIGI)).isTrue();
    assertThat(A1.isOwner(UserTest.SANJIGI)).isFalse();
  }
}
