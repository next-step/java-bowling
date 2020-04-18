package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import qna.CannotDeleteException;

public class AnswersTest {

  @Test
  public void deleteSuccessfully() throws CannotDeleteException {
    Answer answer1 = AnswerTest.answerBy(UserTest.JAVAJIGI);
    Answer answer2 = AnswerTest.answerBy(UserTest.JAVAJIGI);
    Answers answers = new Answers(Arrays.asList(answer1, answer2));

    List<DeleteHistory> deleteHistories = answers.deleteBy(UserTest.JAVAJIGI);

    assertThat(answer1.isDeleted()).isTrue();
    assertThat(answer2.isDeleted()).isTrue();
    assertThat(deleteHistories).hasSize(2);
  }

  @Test
  public void deleteByAnotherUser() {
    Answer answer1 = AnswerTest.answerBy(UserTest.JAVAJIGI);
    Answer answer2 = AnswerTest.answerBy(UserTest.JAVAJIGI);
    Answers answers = new Answers(Arrays.asList(answer1, answer2));

    assertThatThrownBy(() -> answers.deleteBy(UserTest.SANJIGI))
        .isInstanceOf(CannotDeleteException.class);
  }
}
