package qna.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private static Stream<Arguments> answerAndUser() {
        return Stream.of(
                Arguments.of(A1, UserTest.SANJIGI),
                Arguments.of(A2, UserTest.JAVAJIGI)
        );
    }

    @ParameterizedTest(name = "답변 여부에 따른 삭제 예외 처리")
    @MethodSource("answerAndUser")
    void isOwner(Answer answer, User user) {
        assertThatThrownBy(() -> answer.isAnotherUserAnswer(user)).isExactlyInstanceOf(CannotDeleteException.class);
    }
}
