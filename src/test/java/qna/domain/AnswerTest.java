package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변 삭제 확인")
    @ParameterizedTest
    @MethodSource(value = "provideAnswer")
    void delete(Answer answer) {
        assertThat(answer.isDeleted()).isFalse();
        answer.delete();

        assertThat(answer.isDeleted()).isTrue();
    }

    private static Stream<Arguments> provideAnswer() {
        return Stream.of(
                Arguments.of(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1")),
                Arguments.of(new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2"))
        );
    }

    @DisplayName("질문자와 답변자 같은지 확인")
    @Test
    void checkOwnerForDelete() {
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        assertDoesNotThrow(() -> answer.checkOwnerForDelete(UserTest.JAVAJIGI));
    }

    @DisplayName("질문자와 답변자 다르면 예외")
    @Test
    void checkOwnerForDelete_fail() {
        Answer answer = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents1");

        assertThatThrownBy(() -> answer.checkOwnerForDelete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(Answer.CANNOT_DELETE_MESSAGE);
    }
}
