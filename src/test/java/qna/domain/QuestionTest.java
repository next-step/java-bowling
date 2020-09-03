package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
  public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
  public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

  @Test
  void delete_성공() throws CannotDeleteException {
    Q1.delete(UserTest.JAVAJIGI);

    assertThat(Q1.isDeleted()).isTrue();
  }

  @Test
  void delete_다른_사람이_쓴_글() {
    assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
  }

  @Test
  void delete_성공_질문자_답변자_같음() throws CannotDeleteException {
    Q1.addAnswer(AnswerTest.A1);  // 질문자 답변자 같음
    Q1.delete(UserTest.JAVAJIGI);

    assertThat(Q1.isDeleted()).isTrue();
    Q1.getAnswers().forEach(answer -> assertThat(answer.isDeleted()).isTrue());
  }

  @Test
  void delete_답변_중_다른_사람이_쓴_글() {
    Q1.addAnswer(AnswerTest.A1);  // 질문자 답변자 같음
    Q1.addAnswer(AnswerTest.A2);  // 질문자 답변자 다름

    assertThatThrownBy(() -> Q1.delete(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
  }
}
