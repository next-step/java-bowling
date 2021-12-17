package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @ParameterizedTest
    @DisplayName("삭제 기능 구현")
    @MethodSource(value = "createQuestion")
    void deleteTest(Question question, User loginUser) throws CannotDeleteException {
        question.delete(loginUser);
        assertThat(question.isDeleted()).isTrue();
    }

    private static Stream<Arguments> createQuestion() {

        return Stream.of(
                Arguments.of(Q1, UserTest.JAVAJIGI),
                Arguments.of(Q2, UserTest.SANJIGI)
        );
    }

    @ParameterizedTest
    @DisplayName("질문 작성자와 로그인 유저가 다른경우 예외처리 검증")
    @MethodSource(value = "createFailQuestion")
    void userExceptionTest(Question question, User loginUser) {
        assertThatThrownBy(() ->
                question.delete(loginUser)).isInstanceOf(CannotDeleteException.class);
    }

    private static Stream<Arguments> createFailQuestion() {

        return Stream.of(
                Arguments.of(Q1, UserTest.SANJIGI),
                Arguments.of(Q2, UserTest.JAVAJIGI)
        );
    }

    @DisplayName("질문을 삭제할 때, 답변의 삭제 또한 삭제 상태(deleted)를 변경")
    @ParameterizedTest
    @MethodSource(value = "provideQuestionAndUserAndSelfAnswer")
    void assert_answer_deleted(Question question, User loginUser) throws CannotDeleteException {
        Answers answers = question.getAnswers();

        answers.getAnswers().forEach(answer -> assertThat(answer.isDeleted()).isFalse());
        question.delete(loginUser);

        answers.getAnswers().forEach(answer -> assertThat(answer.isDeleted()).isTrue());
    }

    private static Stream<Arguments> provideQuestionAndUserAndSelfAnswer() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "Answers Contents1"));
        return Stream.of(
                Arguments.of(question, UserTest.JAVAJIGI)
        );
    }

}
