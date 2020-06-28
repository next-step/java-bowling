package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("Question deleteQuestionTest() 메소드 테스트")
    @ParameterizedTest
    @MethodSource("provideQuestion")
    void deleteQuestionTest(final Answer answer, final DeleteHistories expected) throws Exception {
        Q1.addAnswer(answer);
        DeleteHistories deleteHistories = Q1.deleteQuestion();
        assertThat(deleteHistories).isEqualTo(expected);
    }

    private static Stream<Arguments> provideQuestion() {
        return Stream.of(
                Arguments.of(AnswerTest.A1, DeleteHistories.of(Arrays.asList(deleteHistory1(), deleteHistory2())))
        );
    }

    private static DeleteHistory deleteHistory1() {
        return DeleteHistory.of(ContentType.QUESTION, null, UserTest.JAVAJIGI, LocalDateTime.now());
    }

    private static DeleteHistory deleteHistory2() {
        return DeleteHistory.of(ContentType.ANSWER, null, UserTest.JAVAJIGI, LocalDateTime.now());
    }

    @DisplayName("Question deleteQuestionTest() 메소드 예외 테스트 :: CannotDeleteException")
    @ParameterizedTest
    @MethodSource("provideCannotDeleteExceptionQuestion")
    void deleteQuestionCannotDeleteExceptionTest(final Answer answer) {
        Q1.addAnswer(answer);
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(Q1::deleteQuestion);
    }

    private static Stream<Arguments> provideCannotDeleteExceptionQuestion() {
        return Stream.of(
                Arguments.of(AnswerTest.A2)
        );
    }
}
