package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.exception.CannotDeleteException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @ParameterizedTest
    @MethodSource("injectAnswerWriter")
    @DisplayName("삭제를 성공한다.")
    void deleteSuccess(Answer answer, User writer) throws CannotDeleteException {
        answer.deleteAnswer(writer);
        assertThat(answer.isDeleted()).isTrue();
    }


    private static Stream<Arguments> injectAnswerWriter() {
        return Stream.of(
                Arguments.of(A1, UserTest.JAVAJIGI),
                Arguments.of(A2, UserTest.SANJIGI)
        );
    }

    @ParameterizedTest
    @MethodSource("injectAnswerAnotherWriterDelete")
    @DisplayName("질문자 이외에 다른사람이 답변단걸 삭제할경우 익셉션 발생.")
    void throwDeleteAnotherWriter(Answer answer, User writer) throws CannotDeleteException {
        assertThatThrownBy(() -> answer.deleteAnswer(writer))
                .isInstanceOf(CannotDeleteException.class);
    }


    private static Stream<Arguments> injectAnswerAnotherWriterDelete() {
        return Stream.of(
                Arguments.of(A1, UserTest.SANJIGI),
                Arguments.of(A2, UserTest.JAVAJIGI)
        );
    }

}
