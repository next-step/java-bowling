package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {

  public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
  public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

  @Test
  @DisplayName("본인 글 삭제")
  void shouldBeDeleted() {
    Q1.delete(UserTest.JAVAJIGI);
    assertThat(Q1.isDeleted()).isTrue();
  }

  @Test
  @DisplayName("본인이 아닐경우 오류")
  void delete_ShouldBeException() {
    assertThatThrownBy(() -> Q2.delete(UserTest.JAVAJIGI))
        .isInstanceOf(CannotDeleteException.class);
  }
}
