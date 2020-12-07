package qna.domain;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;


public class AnswersTest {
    @Test
    public void addAnswerTest() {
        Answers answers = new Answers();
        int sizeBefore = answers.size();
        answers.addAnswer(QuestionTest.Q1, AnswerTest.A1);
        int sizeAfter = answers.size();

        assertThat(sizeAfter - sizeBefore).isEqualTo(1);
    }

    @ParameterizedTest
    @MethodSource("answers")
    public void deleteAllTest(Answers answers, User loginUser) throws CannotDeleteException {
        assertThatCode(() -> answers.deleteAll(loginUser))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> answers() {
        return Stream.of(
                Arguments.of(AnswerTest.A1, UserTest.JAVAJIGI),
                Arguments.of(AnswerTest.A2, UserTest.SANJIGI)
        );
    }
}
