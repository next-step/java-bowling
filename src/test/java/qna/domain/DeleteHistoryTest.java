package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class DeleteHistoryTest {

  @Test
  void equals_성공() {
    LocalDateTime deletedTime = LocalDateTime.now();
    DeleteHistory expected = new DeleteHistory(
        ContentType.QUESTION, 1L, UserTest.JAVAJIGI, deletedTime
    );
    DeleteHistory actual = new DeleteHistory(
        ContentType.QUESTION, 1L, UserTest.JAVAJIGI, deletedTime
    );

    assertThat(actual).isEqualTo(expected);
  }
}
