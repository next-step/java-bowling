package qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void when_setDeleted(boolean deleted) {
        A1.setDeleted(deleted);
        A2.setDeleted(deleted);
        assertThat(A1.isDeleted()).isEqualTo(deleted);
        assertThat(A2.isDeleted()).isEqualTo(deleted);
    }

    @ParameterizedTest
    @MethodSource("isOwnerParams")
    public void when_isOwner_given_user(Answer answer, User user, boolean isOwner) {
        assertThat(answer.isOwner(user)).isEqualTo(isOwner);
    }

    @Test
    public void when_toQuestion_Q2() {
        A1.toQuestion(QuestionTest.Q2);
        A2.toQuestion(QuestionTest.Q2);
    }

    public static Stream<Arguments> isOwnerParams() {
        return Stream.of(
                Arguments.of(A1, UserTest.JAVAJIGI, true),
                Arguments.of(A1, UserTest.SANJIGI, false),
                Arguments.of(A2, UserTest.JAVAJIGI, false),
                Arguments.of(A2, UserTest.SANJIGI, true)
                );
    }
}
