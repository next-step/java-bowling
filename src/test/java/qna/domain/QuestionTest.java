package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("삭제가 가능한 user 확인 테스트")
    @Test
    void validateOwner_valid_user() {
        User loginUser = UserTest.JAVAJIGI;

        assertThatCode(() -> Q1.validateOwner(loginUser))
                .doesNotThrowAnyException();
    }

    @DisplayName("삭제가 불가능한 user 확인 테스트")
    @Test
    void validateOwner_invalid_user() {
        User loginUser = UserTest.SANJIGI;

        String expectedMessage = "질문을 삭제할 권한이 없습니다.";

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Q1.validateOwner(loginUser))
                .withMessage(expectedMessage);
    }

    @DisplayName("삭제 처리 테스트")
    @ParameterizedTest
    @MethodSource("deleteByTestData")
    void deleteBy(Question question, User loginUser) {
        DeleteHistories deleteHistories = question.deleteBy(loginUser);

        assertThat(deleteHistories).isNotNull();
        assertThat(question.isDeleted()).isTrue();
    }

    private static Stream<Arguments> deleteByTestData() {
        return Stream.of(
                Arguments.of(Q1, UserTest.JAVAJIGI),
                Arguments.of(Q2, UserTest.SANJIGI)
        );
    }
}
