package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.NotOwnedDeleteException;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AnswersTest {

    @DisplayName("삭제할 답변 목록 중 작성자의 답변이 아닌게 있을 경우 NotOwnedDeleteException 발생")
    @ParameterizedTest
    @MethodSource("provideAnswersAndUser")
    public void 삭제_검증_테스트 (Answers answers, User user) {
        assertThatExceptionOfType(NotOwnedDeleteException.class)
            .isThrownBy(() -> answers.validateDelete(user));
    }

    private static Stream<Arguments> provideAnswersAndUser () {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);
        return Stream.of(
            Arguments.of(answers, UserTest.JAVAJIGI),
            Arguments.of(answers, UserTest.SANJIGI)
        );
    }
}
