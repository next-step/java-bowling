package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
  public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
  public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

  @Test
  void delete_성공() throws CannotDeleteException {
    List<DeleteHistory> deleteHistories = Q1.delete(UserTest.JAVAJIGI);

    assertThat(Q1.isDeleted()).isTrue();
    assertThat(deleteHistories)
        .isEqualTo(
            Arrays.asList(
                new DeleteHistory(
                    ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now())));
  }

  @Test
  void delete_다른_사람이_쓴_글() {
    assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
  }

  @Test
  void delete_성공_질문자_답변자_같음() throws CannotDeleteException {
    Q1.addAnswer(AnswerTest.A1); // 질문자 답변자 같음
    List<DeleteHistory> deleteHistories = Q1.delete(UserTest.JAVAJIGI);

    assertThat(Q1.isDeleted()).isTrue();
    Q1.getAnswers().forEach(answer -> assertThat(answer.isDeleted()).isTrue());
    assertThat(deleteHistories)
        .isEqualTo(
            Arrays.asList(
                new DeleteHistory(
                    ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()),
                new DeleteHistory(
                    ContentType.ANSWER,
                    AnswerTest.A1.getId(),
                    AnswerTest.A1.getWriter(),
                    LocalDateTime.now())));
  }

  @Test
  void delete_답변_중_다른_사람이_쓴_글() {
    Q2.addAnswer(AnswerTest.A1); // 질문자 답변자 다름
    Q2.addAnswer(AnswerTest.A2); // 질문자 답변자 같음

    assertThatThrownBy(() -> Q2.delete(UserTest.JAVAJIGI))
        .isInstanceOf(CannotDeleteException.class);
  }
}
