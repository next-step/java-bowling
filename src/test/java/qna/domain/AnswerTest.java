package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("질문 삭제 테스트")
    @ParameterizedTest
    @MethodSource("provideAnswer")
    public void 질문_삭제_테스트 (Answer answer) {
        assertEquals(
            new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()),
            answer.delete());
    }

    private static Stream<Arguments> provideAnswer () {
        return Stream.of(
            Arguments.of(A1),
            Arguments.of(A2)
        );
    }
}
