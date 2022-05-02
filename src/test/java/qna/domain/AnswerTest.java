package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AnswerTest {

    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @ParameterizedTest(name = "{displayName}[{index}] it is {1} that answerWrittenJavajigi is not Owner by {0}")
    @MethodSource
    @DisplayName("본인의 답변이 아닌지 판단")
    void isNotOwner(User writer, boolean expected) {
        //given
        Answer answerWrittenJavajigi = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Contents");
        //when, then
        assertThat(answerWrittenJavajigi.isNotOwner(writer))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("삭제")
    void delete() {
        //given
        Answer answer = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Contents");
        //when
        DeleteHistory history = answer.delete();
        //then
        assertAll(
                () -> assertThat(answer.isDeleted()).isTrue(),
                () -> assertThat(history).isNotNull()
        );
    }

    private static Stream<Arguments> isNotOwner() {
        return Stream.of(
                Arguments.of(UserTest.SANJIGI, true),
                Arguments.of(UserTest.JAVAJIGI, false)
        );
    }
}
