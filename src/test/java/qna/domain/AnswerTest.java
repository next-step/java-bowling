package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.exception.CannotDeleteException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @ParameterizedTest
    @MethodSource("injectAnswer")
    @DisplayName("삭제를 성공한다.")
    void deleteSuccess(Answer answer) throws CannotDeleteException {
        DeleteHistories deleteHistories = DeleteHistories.of();

        assertThat(answer.deleteAnswer(answer.getWriter(), deleteHistories)).isEqualTo(true);
    }


    private static Stream<Arguments> injectAnswer() {
        return Stream.of(
                Arguments.of(A1),
                Arguments.of(A2)
        );
    }


}
