package qna.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private static Stream<Arguments> questionAndUser() {
        return Stream.of(
                Arguments.of(Q1, UserTest.SANJIGI),
                Arguments.of(Q2, UserTest.JAVAJIGI)
        );
    }

    @ParameterizedTest(name = "삭제 권한이 없는 유저 예외 처리 - {index}")
    @MethodSource("questionAndUser")
    void test(Question question, User user) {
        assertThatThrownBy(() -> question.hasDeleteAccess(user)).isExactlyInstanceOf(CannotDeleteException.class);
    }
}
