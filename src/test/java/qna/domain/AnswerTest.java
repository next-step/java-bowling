package qna.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

public class AnswerTest {

  public static final Answer A1 = new Answer(
      UserTest.JAVAJIGI,
      QuestionTest.Q1,
      "Answers Contents1"
  );
  public static final Answer A2 = new Answer(
      UserTest.SANJIGI,
      QuestionTest.Q1,
      "Answers Contents2"
  );

  @ParameterizedTest
  @MethodSource("provideAnswerWithLoginUser")
  void delete_성공(Answer answer, User loginUser) throws Exception {

    answer.delete(loginUser);

    assertThat(answer.isDeleted()).isTrue();
  }

  static Stream<Arguments> provideAnswerWithLoginUser() {
    return Stream.of(
        arguments(
            A1,
            UserTest.JAVAJIGI
        ),
        arguments(
            A2,
            UserTest.SANJIGI
        )
    );
  }

  @ParameterizedTest
  @MethodSource("provideAnswerWithInvalidLoginUser")
  void delete_실패(Answer answer, User loginUser) throws Exception {

    assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() -> {
      answer.delete(loginUser);
    });

  }

  static Stream<Arguments> provideAnswerWithInvalidLoginUser() {
    return Stream.of(
        arguments(
            A1,
            UserTest.SANJIGI
        ),
        arguments(
            A2,
            UserTest.JAVAJIGI
        )
    );
  }
}
