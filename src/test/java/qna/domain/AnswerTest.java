package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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
                Arguments.of(A1),
                Arguments.of(A2)
        );
    }
}
