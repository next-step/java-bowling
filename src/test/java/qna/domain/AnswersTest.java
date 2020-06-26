package qna.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;
import qna.NotFoundException;

class AnswersTest {

  public static Answers ANSWERS1 = new Answers();
  public static Answers ANSWERS2 = new Answers();
  private static Answer ANSWER1 = new Answer(
      (long) 1,
      UserTest.JAVAJIGI,
      QuestionTest.Q1,
      "Answers Contents1"
  );
  private static Answer ANSWER2 = new Answer(
      (long) 2,
      UserTest.SANJIGI,
      QuestionTest.Q1,
      "Answers Contents2"
  );

  @AfterEach
  void tearDown() {
    ANSWERS1 = new Answers();
    ANSWERS2 = new Answers();

    ANSWER1 = new Answer(
        (long) 1,
        UserTest.JAVAJIGI,
        QuestionTest.Q1,
        "Answers Contents1"
    );
    ANSWER2 = new Answer(
        (long) 2,
        UserTest.SANJIGI,
        QuestionTest.Q1,
        "Answers Contents2"
    );
  }

  @ParameterizedTest
  @MethodSource("provideAnswerWithId")
  void addAnswer(Answer answer, long id) {
    ANSWERS1.addAnswer(answer);

    assertThat(ANSWERS1.getAnswerById(id)).isEqualTo(answer);
  }

  static Stream<Arguments> provideAnswerWithId() {
    return Stream.of(
        arguments(
            new Answer(
                (long) 1,
                UserTest.JAVAJIGI,
                QuestionTest.Q1,
                "Answers Contents1"
            ),
            1
        ),
        arguments(
            new Answer(
                (long) 2,
                UserTest.JAVAJIGI,
                QuestionTest.Q1,
                "Answers Contents1"
            ),
            2
        )
    );
  }

  @ParameterizedTest
  @MethodSource("provideAnswerWithInvalidId")
  void addAnswer_실패(Answer answer, long id) {
    ANSWERS1.addAnswer(answer);

    assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> {
      ANSWERS1.getAnswerById(id);
    });
  }

  static Stream<Arguments> provideAnswerWithInvalidId() {
    return Stream.of(
        arguments(
            new Answer(
                (long) 1,
                UserTest.JAVAJIGI,
                QuestionTest.Q1,
                "Answers Contents1"
            ),
            2
        ),
        arguments(
            new Answer(
                (long) 2,
                UserTest.JAVAJIGI,
                QuestionTest.Q1,
                "Answers Contents1"
            ),
            1
        )
    );
  }

  @ParameterizedTest
  @MethodSource("provideAnswersContainAnswerWithLoginUserAndId")
  void delete(Answers answers, User loginUser, long id) {
    answers.delete(loginUser);

    assertThat(answers.getAnswerById(id).isDeleted()).isTrue();
  }

  static Stream<Arguments> provideAnswersContainAnswerWithLoginUserAndId() {
    ANSWERS1.addAnswer(ANSWER1);
//    ANSWERS1.addAnswer(ANSWER2);

    return Stream.of(
        arguments(
            ANSWERS1,
            ANSWER1.getWriter(),
            ANSWER1.getId()
        )
    );
  }

  @ParameterizedTest
  @MethodSource("provideAnswersContainAnswerWithInvalidLoginUserAndId")
  void delete_실패(Answers answers, User loginUser, long id) {

    assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() -> {
      answers.delete(loginUser);
    });
  }

  static Stream<Arguments> provideAnswersContainAnswerWithInvalidLoginUserAndId() {
    ANSWERS1.addAnswer(ANSWER1);
    ANSWERS1.addAnswer(ANSWER2);

    return Stream.of(
        arguments(
            ANSWERS1,
            ANSWER1.getWriter(),
            ANSWER1.getId()
        )
    );
  }

  @ParameterizedTest
  @MethodSource("provideAnswersDeleted")
  void getDeleteHistories(Answers answers, List<DeleteHistory> expected) {
    List<DeleteHistory> deleteHistories = answers.getDeleteHistories();

    assertThat(deleteHistories).isEqualTo(expected);
  }

  static Stream<Arguments> provideAnswersDeleted() {
    ANSWERS1.addAnswer(ANSWER1);

    ANSWERS1.delete(ANSWER1.getWriter());

    return Stream.of(
        arguments(
            ANSWERS1,
            Arrays.asList(
                DeleteHistory.createBy(ANSWER1)
            )
        )
    );
  }
}