package qna.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.domain.*;
import qna.exceptions.CannotDeleteException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionDeleteTest {

    @DisplayName("질문 삭제 테스트")
    @ParameterizedTest
    @MethodSource("provideQuestionAndUser")
    void deleteQuestionByUser(Question question, User loginUser) throws CannotDeleteException {
        DeleteHistories histories = question.delete(loginUser);
        assertThat(question.isDeleted()).isTrue();
    }

    private static Stream<Arguments> provideQuestionAndUser() {
        return Stream.of(
                Arguments.of(QuestionTest.Q1, UserTest.JAVAJIGI),
                Arguments.of(QuestionTest.Q2, UserTest.SANJIGI)
        );
    }

    @DisplayName("다른 작성자의 글을 삭제 테스트")
    @ParameterizedTest
    @MethodSource("provideUnMatchQuestionAndUser")
    void deleteNotMatchedQuestionByUser(Question question, User loginUser) {
        assertThatThrownBy(() -> question.delete(loginUser))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(Question.ERROR_DENIED_DELETE_PERMISSION);

    }

    private static Stream<Arguments> provideUnMatchQuestionAndUser() {
        return Stream.of(
                Arguments.of(QuestionTest.Q1, UserTest.SANJIGI),
                Arguments.of(QuestionTest.Q2, UserTest.JAVAJIGI)
        );
    }

    @DisplayName("답변이 없는 경우 질문 삭제 테스트")
    @ParameterizedTest
    @MethodSource("provideQuestionOfNotExistsAnswer")
    void deleteNotExistsAnswerQuestion(Question question, User loginUser) throws CannotDeleteException {
        question.delete(loginUser);
        assertThat(question.isDeleted()).isTrue();
    }

    private static Stream<Arguments> provideQuestionOfNotExistsAnswer() {
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        return Stream.of(
                Arguments.of(question, UserTest.JAVAJIGI)
        );
    }

    @DisplayName("질문자와 답변자가 다른 경우 테스트")
    @ParameterizedTest
    @MethodSource("provideQuestionOfNotMatchedAnswer")
    void deleteNotMatchedAnswerQuestion(Question question, User loginUser) throws CannotDeleteException {
        assertThatThrownBy(()-> question.delete(loginUser))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(Question.ERROR_EXISTS_OTHER_WRITTEN);
    }

    private static Stream<Arguments> provideQuestionOfNotMatchedAnswer() {
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(AnswerTest.A2);
        return Stream.of(
                Arguments.of(question, UserTest.JAVAJIGI)
        );
    }

    @DisplayName("질문 삭제 후 답변 삭제상태 테스트")
    @ParameterizedTest
    @MethodSource("provideQuestionWithAnswerAndUser")
    void deleteQuestionAndAnswer(Question question, User loginUser) throws CannotDeleteException {
        assertThat(question.isDeleted()).isFalse();

        question.delete(loginUser);

        Answers answers = question.getAnswers();
        answers.getAnswers()
                .forEach(answer -> assertThat(answer.isDeleted()).isTrue());

    }

    private static Stream<Arguments> provideQuestionWithAnswerAndUser() {
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(AnswerTest.A1);
        return Stream.of(
                Arguments.of(question, UserTest.JAVAJIGI)
        );
    }

}
