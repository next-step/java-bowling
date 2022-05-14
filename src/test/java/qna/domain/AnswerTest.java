package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnswerTest {

  public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1,
      "Answers Contents1");
  public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1,
      "Answers Contents2");

  private Answer answer;
  private Long answerId;

  @BeforeEach
  void setup() {
    answerId = 1L;
    answer = new Answer(answerId, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
  }

  @Test
  void isWrittenBy_true_반환() {
    assertThat(answer.isWrittenBy(UserTest.JAVAJIGI)).isTrue();
  }

  @Test
  void isWrittenBy_false_반환() {
    assertThat(answer.isWrittenBy(UserTest.SANJIGI)).isFalse();
  }

  @Test
  void delete_성공() {
    LocalDateTime deletedTime = LocalDateTime.now();
    DeleteHistory expected = new DeleteHistory(
        ContentType.ANSWER,
        answerId,
        UserTest.JAVAJIGI,
        deletedTime
    );
    assertThat(answer.delete(UserTest.JAVAJIGI, deletedTime)).isEqualTo(expected);
  }
}
