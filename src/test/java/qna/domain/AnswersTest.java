package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {
    private Answers answers = new Answers();

    @BeforeEach
    void setUp() {
        answers.add(new Answer(UserTest.JAVAJIGI, "content1"));
        answers.add(new Answer(UserTest.SANJIGI, "content2"));
    }

    @ParameterizedTest
    @DisplayName("본인이 작성한 답변이 아닌경우 삭제시 예외 발생")
    @MethodSource
    void deleteException(User loginUser) {
        assertThatThrownBy(() -> answers.deleteAnswers(loginUser)).isInstanceOf(CannotDeleteException.class);
    }

    static Stream<Arguments> deleteException() {
        return Stream.of(
                Arguments.of(
                        UserTest.SANJIGI
                ),
                Arguments.of(
                        UserTest.JAVAJIGI
                )
        );
    }

}