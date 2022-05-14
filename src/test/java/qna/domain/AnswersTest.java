package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnswersTest {

  private Answers answers;

  @BeforeEach
  void setup(){
    answers = new Answers();
  }

  @Test
  void add_성공() {
    answers.add(AnswerTest.A1);
    answers.add(AnswerTest.A2);
    Answers expected = new Answers(List.of(AnswerTest.A1, AnswerTest.A2));

    assertThat(answers).isEqualTo(expected);
  }

  @Test
  void deleteAllAnswerWrittenBy_성공() {
    Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    answers.add(answer);
    LocalDateTime deletedTime = LocalDateTime.now();
    Answers expectedAnswers = new Answers(Collections.emptyList());
    DeleteHistory expectedHistory = answer.delete(UserTest.JAVAJIGI, deletedTime);

    DeleteHistories deleteHistories = answers.deleteAllAnswerWrittenBy(
        UserTest.JAVAJIGI, deletedTime
    );

    assertThat(answers).isEqualTo(expectedAnswers);
    assertThat(deleteHistories).isEqualTo(DeleteHistories.from(expectedHistory));
  }

  @Test
  void hasAnswerNotWrittenBy_true_반환() {
    answers.add(AnswerTest.A1);

    assertThat(answers.hasAnswerNotWrittenBy(UserTest.SANJIGI)).isTrue();
  }

  @Test
  void hasAnswerNotWrittenBy_false_반환() {
    answers.add(AnswerTest.A1);

    assertThat(answers.hasAnswerNotWrittenBy(UserTest.JAVAJIGI)).isFalse();
  }
}
