package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

class AnswersTest {

  @DisplayName("질문들의 작성자가 현재 사용자인지 검증.")
  @Test
  void validationOwners() {
    Answers answers = new Answers(Collections.singletonList(AnswerTest.A2));

    assertThatThrownBy(() -> answers.isOwners(UserTest.JAVAJIGI))
        .isInstanceOf(CannotDeleteException.class);
  }

  @DisplayName("삭제대상인 질문들 객체 생성.")
  @Test
  void deleteAnswers() {
    Answers answers = new Answers(Collections.singletonList(AnswerTest.A2));
    List<DeleteHistory> deleteHistories = answers.deleteAnswers();

    assertThat(deleteHistories.size()).isEqualTo(1);
  }
}