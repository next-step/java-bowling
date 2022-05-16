package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

class AnswersTest {

  @DisplayName("삭제하려는 답변 중에 본인이 작성하지 않은 답변이 하나라도 있으면 예외가 발생한다.")
  @Test
  void checkDeletePermissions() {
    Answers answers = new Answers();
    answers.add(AnswerTest.A1);
    answers.add(AnswerTest.A2);

    assertThatThrownBy(() -> answers.checkDeletePermissions(UserTest.SANJIGI))
        .isInstanceOf(CannotDeleteException.class);
  }

  @Test
  void deleteAndAddHistories() {
    Answer answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    Answer answer2 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
    Answers answers = new Answers();
    answers.add(answer1);
    answers.add(answer2);
    DeleteHistories deleteHistories = new DeleteHistories();

    DeleteHistories addedDeleteHistories = answers.deleteAndAddHistories(deleteHistories);

    assertAll(
        () -> assertThat(answer1.isDeleted()).isTrue(),
        () -> assertThat(answer2.isDeleted()).isTrue(),
        () -> assertThat(addedDeleteHistories.size()).isEqualTo(2)
    );
  }
}
