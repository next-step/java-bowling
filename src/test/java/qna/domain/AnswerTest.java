package qna.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

public class AnswerTest {

  public static Answer A1;
  public static Answer A2;

  static {
    initAnswerTest();
  }

  @AfterEach
  void tearDown() {
    initAnswerTest();
  }

  public static void initAnswerTest() {
    A1 = Answer.getBuilder(UserTest.JAVAJIGI, QuestionTest.Q1)
        .contents("Answers Contents1")
        .build();

    A2 = Answer.getBuilder(UserTest.SANJIGI, QuestionTest.Q1)
        .contents("Answers Contents2")
        .build();
  }

  @ParameterizedTest
  @MethodSource("provideAnswerWithValidLoginUser")
  void isOwner(Answer answer, User loginUser) {
    assertThat(answer.isOwner(loginUser)).isTrue();
  }

  static Stream<Arguments> provideAnswerWithValidLoginUser() {
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
  @MethodSource("provideAnswerWithQuestion")
  void toQuestion(Answer answer, Question question, Answer expected) {
    answer.toQuestion(question);

    assertThat(answer).isEqualTo(expected);
  }

  static Stream<Arguments> provideAnswerWithQuestion() {
    return Stream.of(
        arguments(
            Answer.getBuilder(UserTest.JAVAJIGI, QuestionTest.Q1)
                .contents("Answers Contents1")
                .build(),
            QuestionTest.Q2,
            Answer.getBuilder(UserTest.JAVAJIGI, QuestionTest.Q2)
                .contents("Answers Contents2")
                .build()
        ),
        arguments(
            Answer.getBuilder(UserTest.JAVAJIGI, QuestionTest.Q2)
                .contents("Answers Contents2")
                .build(),
            QuestionTest.Q1,
            Answer.getBuilder(UserTest.JAVAJIGI, QuestionTest.Q1)
                .contents("Answers Contents2")
                .build()
        )
    );
  }

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
