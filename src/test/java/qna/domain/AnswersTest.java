package qna.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswersTest {

  @Test
  void delete_실패_질문자와_다른_답변자가_있음() {
    Answers answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));

    assertThatThrownBy(() -> answers.deleteByUser(UserTest.SANJIGI))
        .isInstanceOf(CannotDeleteException.class);
  }
}
