package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.domain.answer.AnswerTest;
import qna.error.MatchingQuestionUserAndAnswerUserException;
import qna.error.NotEnoughPermissionException;

public class QuestionTest {
  public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
  public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

  @Test
  @DisplayName("생성 테스트")
  void createTest() {
    Question newQuestion = new Question("질문있어요", "사실 없어요").writeBy(UserTest.JAVAJIGI);
  }

  @Test
  @DisplayName("다른 사람의 글일 경우 삭제 불가능")
  void invalidDeleteTest() {
    Question Q3 = new Question("title3", "contents3").writeBy(UserTest.SANJIGI);

    Assertions.assertThatThrownBy(() -> Q3.delete(UserTest.JAVAJIGI)).isInstanceOf(NotEnoughPermissionException.class);
  }

  @Test
  @DisplayName("질문자와 답변자가 다른 경우 삭제 불가능")
  void invalidDeleteQuestionAndAnswersTest() {
    Question Q4 = new Question("title4", "contents4").writeBy(UserTest.JAVAJIGI);
    Q4.addAnswer(AnswerTest.A2);

    Assertions.assertThatThrownBy(() -> Q4.delete(UserTest.JAVAJIGI)).isInstanceOf(MatchingQuestionUserAndAnswerUserException.class);
  }

  @Test
  @DisplayName("정상 삭제 테스트")
  void validDeleteQuestionAndAnswersTest() {
    Question Q5 = new Question("title4", "contents4").writeBy(UserTest.JAVAJIGI);
    Q5.addAnswer(AnswerTest.A1);

    Assertions.assertThat(Q5.delete(UserTest.JAVAJIGI).size()).isEqualTo(2);
  }
}
