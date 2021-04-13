package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.AnswerTest.A1;
import static qna.domain.QuestionTest.Q1;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeleteHistoryTest {

  @Test
  @DisplayName("[DeleteHistory] Question을 변수로 생성자 테스트")
  void create_question_history() {
    DeleteHistory deleteHistory = new DeleteHistory(Q1, LocalDateTime.now());
    assertThat(deleteHistory).isEqualTo(new DeleteHistory(Q1, LocalDateTime.now()));
  }

  @Test
  @DisplayName("[DeleteHistory] Answer을 변수로 생성자 테스트")
  void create_answer_history() {
    DeleteHistory deleteHistory = new DeleteHistory(A1, LocalDateTime.now());
    assertThat(deleteHistory).isEqualTo(new DeleteHistory(A1, LocalDateTime.now()));
  }
}
