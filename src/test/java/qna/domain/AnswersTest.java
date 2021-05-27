package qna.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

class AnswersTest {
  public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
  public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

  @Test
  @DisplayName("다수의 Answer를 담은 후에 각각의 Answer Entity를 비교할 수 있다.")
  public void save_many_answer_and_compare() throws Exception {
      //given
      Answers answers = new Answers();

      //when
      answers.add(A1);
      answers.add(A2);

      //then
      List<Answer> answerList = answers.of();
      assertAll(
          () -> assertEquals(answerList.get(0), A1),
          () -> assertEquals(answerList.get(1), A2)
      );
  }

  @Test
  @DisplayName("로그인하지 않은 사용자의 글을 확인하는 경우 CannotDeleteException이 발생한다.")
  public void is_login_user_throw_exception() throws Exception {
    //given
    Answers answers = new Answers();

    //when
    answers.add(A1);

    CannotDeleteException thrown = assertThrows(CannotDeleteException.class,
        () -> answers.checkOwner(UserTest.SANJIGI)
    );

    //then
    assertTrue(thrown.getMessage().contains("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다."));
  }
}