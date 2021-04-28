package qna.domain.answer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.domain.UserTest;
import qna.error.MatchingQuestionUserAndAnswerUserException;

import java.util.ArrayList;
import java.util.List;

class AnswersTest {

  @DisplayName("생성 테스트")
  @Test
  void createTest(){
    List<Answer> answers = new ArrayList<>();
    answers.add(AnswerTest.A1);

    Assertions.assertThat(Answers.of(answers).head().get()).isEqualTo(AnswerTest.A1);
  }

  @DisplayName("정상 삭제 테스트")
  @Test
  void deleteTest(){
    List<Answer> addingAnswers = new ArrayList<>();
    addingAnswers.add(AnswerTest.A1);
    Answers answers = Answers.of(addingAnswers);

    Assertions.assertThat(answers.deleteAll(UserTest.JAVAJIGI).size()).isEqualTo(1);
  }

  @DisplayName("답변자가 모두 일치하지 않는 경우 삭제 수행되지 않음")
  @Test
  void unmatchedUsersDeleteTest(){
    List<Answer> addingAnswers = new ArrayList<>();
    addingAnswers.add(AnswerTest.A1);
    addingAnswers.add(AnswerTest.A2);
    Answers answers = Answers.of(addingAnswers);

    Assertions.assertThatThrownBy(() -> answers.deleteAll(UserTest.JAVAJIGI))
      .isInstanceOf(MatchingQuestionUserAndAnswerUserException.class);
  }
}