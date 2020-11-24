package qna.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.domain.*;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerDeleteTest {


    @DisplayName("질문자 삭제 테스트")
    @Test
    void deleteAnswer() {
        Answer answer = AnswerTest.A1;
        assertThat(answer.isDeleted()).isFalse();

        answer.deleteAnswer();
        assertThat(answer.isDeleted()).isTrue();
    }

    @DisplayName("질문자 일급 콜렉션 삭제 테스트")
    @ParameterizedTest
    @MethodSource("provideAnswers")
    void deleteAnswer(Answers answers) {
        answers.getAnswers()
                .forEach(answer -> assertThat(answer.isDeleted()).isFalse());

        answers.deleteAll();
        answers.getAnswers()
                .forEach(answer -> assertThat(answer.isDeleted()).isTrue());
    }

    private static Stream<Arguments> provideAnswers() {
        Answers answers = Answers.from(Arrays.asList(
                new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"),
                new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2"),
                new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3"),
                new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents4")
        ));
        return Stream.of(
                Arguments.of(answers)
        );
    }

}
