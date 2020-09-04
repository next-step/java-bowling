package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Q1 - Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Q1 - Answers Contents2");
    public static final Answer A3 = new Answer(UserTest.SANJIGI, QuestionTest.Q2, "Q2 - Answers Contents1");

    @DisplayName("삭제가 가능한 user 확인 테스트")
    @Test
    void validateOwner_valid_user() {
        User loginUser = UserTest.SANJIGI;

        assertThatCode(() -> A3.validateOwner(loginUser))
                .doesNotThrowAnyException();
    }

    @DisplayName("삭제가 불가능한 user 확인 테스트")
    @ParameterizedTest
    @MethodSource("validateOwnerInvalidData")
    void validateOwner_invalid_user(Answer answer, User user) {
        String expectedMessage = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> answer.validateOwner(user))
                .withMessage(expectedMessage);
    }

    private static Stream<Arguments> validateOwnerInvalidData() {
        return Stream.of(
                Arguments.of(A1, UserTest.SANJIGI),
                Arguments.of(A2, UserTest.JAVAJIGI)
        );
    }

    @DisplayName("삭제 처리 테스트")
    @ParameterizedTest
    @MethodSource("deleteByTestData")
    void deleteBy(Answer answer, User loginUser) {
        DeleteHistories deleteHistories = answer.deleteBy(loginUser);

        assertThat(deleteHistories).isNotNull();
        assertThat(answer.isDeleted()).isTrue();
    }

    private static Stream<Arguments> deleteByTestData() {
        return Stream.of(
                Arguments.of(A1, UserTest.JAVAJIGI),
                Arguments.of(A2, UserTest.SANJIGI)
        );
    }
}
