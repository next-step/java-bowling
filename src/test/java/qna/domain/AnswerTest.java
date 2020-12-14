package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private static Stream<Arguments> providedSameAnswerWriterAndQuestionWriter() {
        return Stream.of(
                Arguments.arguments(A1, UserTest.JAVAJIGI),
                Arguments.arguments(A2, UserTest.SANJIGI)
        );
    }

    @ParameterizedTest
    @DisplayName("답변자가 같은 경우 삭제가 가능하다")
    @MethodSource("providedSameAnswerWriterAndQuestionWriter")
    void isAnswerWriterAndQuestionOwnerSame(Answer answer, User questionWriter) throws CannotDeleteException {
        assertThat(answer.delete(questionWriter))
                .isEqualTo(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));

    }

    private static Stream<Arguments> providedDifferentAnswerWriterAndQuestionWriter() {
        return Stream.of(
                Arguments.arguments(A1, UserTest.SANJIGI),
                Arguments.arguments(A2, UserTest.JAVAJIGI)
        );
    }

    @ParameterizedTest
    @DisplayName("답변자가 다른 경우 삭제불가, 예외처리 된다")
    @MethodSource("providedDifferentAnswerWriterAndQuestionWriter")
    void isAnswerWriterAndQuestionOwnerDifferent(Answer answer, User questionWriter) {
        assertThatThrownBy(() -> answer.delete(questionWriter))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(Answer.ERROR_DELETE_ANSWER_AUTHORITY_MISMATCH);
    }

}
