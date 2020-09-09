package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문 작성자 검증 로직 성공 테스트")
    @ParameterizedTest
    @MethodSource("provideQuestionAndUser")
    void test_validateOwner_pass(Question question, User user) {
        // when & then
        assertThatCode(() -> question.validateOwner(user))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> provideQuestionAndUser() {
        return Stream.of(
                Arguments.of(Q1, UserTest.JAVAJIGI),
                Arguments.of(Q2, UserTest.SANJIGI)
        );
    }

    @DisplayName("질문 작성자 검증 로직 실패 테스트")
    @ParameterizedTest
    @MethodSource("provideQuestionAndUser4Fail")
    void test_validateOwner_fail(Question question, User user) {
        // when & then
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> question.validateOwner(user));
    }

    private static Stream<Arguments> provideQuestionAndUser4Fail() {
        return Stream.of(
                Arguments.of(Q1, UserTest.SANJIGI),
                Arguments.of(Q2, UserTest.JAVAJIGI)
        );
    }
}
