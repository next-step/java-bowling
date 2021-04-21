package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.QuestionTest.Q1;
import static qna.domain.QuestionTest.Q2;

import java.time.LocalDateTime;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeleteHistoriesTest {

  @Test
  @DisplayName("[DeleteHistories] DeleteHistories 생성 테스트")
  void create_deleteHistories_test() {
    DeleteHistories deleteHistories = new DeleteHistories(Arrays.asList(new DeleteHistory(Q1, LocalDateTime.now()), new DeleteHistory(Q2,LocalDateTime.now())));

    int size = deleteHistories.histories().size();

    assertThat(size).isEqualTo(2);
  }
}
