package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeleteHistoriesTest {

  @DisplayName("DeleteHistory를 추가할 수 있다.")
  @Test
  void add() {
    DeleteHistory deleteHistory = DeleteHistory.of(QuestionTest.Q1);
    DeleteHistories deleteHistories = new DeleteHistories();

    deleteHistories.add(deleteHistory);

    assertThat(deleteHistories.size()).isEqualTo(1);
  }
}
