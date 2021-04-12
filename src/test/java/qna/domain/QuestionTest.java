package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import qna.CannotDeleteException;

public class QuestionTest {

  public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
  public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


  @Test
  @DisplayName("질문자인지 확인")
  public void isOwner() {
    assertThat(Q1.isOwner(UserTest.JAVAJIGI)).isTrue();
  }

  @Test
  @DisplayName("질문 삭제 확인")
  public void isDeleted() {
    Q1.delete();
    assertThat(Q1.isDeleted()).isTrue();
  }

  @Test
  @DisplayName("답변 추가 확인")
  public void addAnswer() {
    Q1.addAnswer(AnswerTest.A1);

    Answers answers = new Answers();
    answers.add(AnswerTest.A1);

    assertThat(Q1.getAnswers()).isEqualTo(answers);
  }

  @Test
  @DisplayName("삭제할 수 있는 사람인지 확인")
  public void validateDelete() {
    assertThatThrownBy(() -> {
      Q1.validateDelete(UserTest.SANJIGI);
    }).isInstanceOf(CannotDeleteException.class);
  }

  @Test
  @DisplayName("quetion을 deletehistory로 변경 확인")
  public void turnQuestionIntoDeleteHistory() throws CannotDeleteException {
    DeleteHistory deleteHistory = DeleteHistory.of(Q1);
    assertThat(Q1.turnQuestionIntoDeleteHistory(UserTest.JAVAJIGI)).isEqualTo(deleteHistory);
  }

}
