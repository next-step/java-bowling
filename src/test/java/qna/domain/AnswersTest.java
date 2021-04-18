package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A2;
import static qna.domain.UserTest.JAVAJIGI;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswersTest {

  @Test
  @DisplayName("작성자가 아닌 답변자가 존재하면, exception")
  void checkOwner_fail() {
    //given
    Answers answers = new Answers(Arrays.asList(A1, A2));
    //when
    //then
    assertThatExceptionOfType(CannotDeleteException.class)
        .isThrownBy(() -> answers.delete(JAVAJIGI))
        .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
  }

  @Test
  void delete() throws CannotDeleteException {
    //given
    Answers answers = new Answers(Arrays.asList(A1));
    //when
    answers.delete(JAVAJIGI);
    //then
    assertThat(answers.getAnswer(0).isDeleted()).isTrue();
  }
}
