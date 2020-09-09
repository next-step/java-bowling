package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3");

    @DisplayName("답변 작성자 검증 로직 성공 테스트")
    @ParameterizedTest
    @MethodSource("provideAnswerAndUser")
    void test_validateOwner_pass(Answer answer, User user) {
        // when & then
        assertThatCode(() -> answer.validateOwner(user))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> provideAnswerAndUser() {
        return Stream.of(
                Arguments.of(A1, UserTest.JAVAJIGI),
                Arguments.of(A2, UserTest.SANJIGI)
        );
    }

    @DisplayName("답변 작성자 검증 로직 실패 테스트")
    @ParameterizedTest
    @MethodSource("provideAnswerAndUser4Fail")
    void test_validateOwner_fail(Answer answer, User user) {
        // when & then
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> answer.validateOwner(user));
    }

    private static Stream<Arguments> provideAnswerAndUser4Fail() {
        return Stream.of(
                Arguments.of(A1, UserTest.SANJIGI),
                Arguments.of(A2, UserTest.JAVAJIGI)
        );
    }
}
