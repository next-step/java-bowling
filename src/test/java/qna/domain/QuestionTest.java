package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswerTest.A2;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제 가능.")
    @ParameterizedTest
    @MethodSource(value = "provideQuestionAndUser")
    void delete(Question question, User loginUser) throws CannotDeleteException {
        question.delete(loginUser);
        assertThat(question.isDeleted()).isTrue();
    }

    private static Stream<Arguments> provideQuestionAndUser() {
        return Stream.of(
                Arguments.of(Q1, UserTest.JAVAJIGI),
                Arguments.of(Q2, UserTest.SANJIGI)
        );
    }

    @DisplayName("로그인 사용자와 질문한 사람이 다른 경우 삭제 불가.")
    @ParameterizedTest
    @MethodSource(value = "provideQuestionAndUserFail")
    void delete_fail(Question question, User loginUser) {
        assertThatThrownBy(() -> question.delete(loginUser))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    private static Stream<Arguments> provideQuestionAndUserFail() {
        return Stream.of(
                Arguments.of(Q1, UserTest.SANJIGI),
                Arguments.of(Q2, UserTest.JAVAJIGI)
        );
    }

    @DisplayName("답변이 없는 경우 삭제가 가능.")
    @ParameterizedTest
    @MethodSource(value = "provideQuestionAndUserWithNoAnswer")
    void delete_no_answer(Question question, User loginUser) throws CannotDeleteException {
        question.delete(loginUser);
        assertThat(question.getAnswers().value()).hasSize(0);
        assertThat(question.isDeleted()).isTrue();
    }

    private static Stream<Arguments> provideQuestionAndUserWithNoAnswer() {
        return Stream.of(
                Arguments.of(new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI), UserTest.JAVAJIGI),
                Arguments.of(new Question("title2", "contents2").writeBy(UserTest.SANJIGI), UserTest.SANJIGI)
        );
    }

    @DisplayName("질문자와 답변글의 모든 답변자가 로그인한 사용자로 같은 경우 삭제가 가능.")
    @ParameterizedTest
    @MethodSource(value = "provideQuestionAndUserWithSelfAnswer")
    void delete_answer_of_self(Question question, User loginUser) throws CannotDeleteException {
        assertThat(question.getAnswers().value().size()).isGreaterThan(0);
        question.delete(loginUser);

        assertThat(question.isDeleted()).isTrue();
    }

    private static Stream<Arguments> provideQuestionAndUserWithSelfAnswer() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "Answers Contents1"));
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "Answers Contents2"));
        return Stream.of(
                Arguments.of(question, UserTest.JAVAJIGI)
        );
    }

    @DisplayName("질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.")
    @ParameterizedTest
    @MethodSource(value = "provideQuestionAndUserWithOthersAnswer")
    void delete_answer_of_others(Question question, User loginUser) {
        assertThat(question.getAnswers().value().size()).isGreaterThan(0);
        assertThatThrownBy(() -> question.delete(loginUser))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(Answer.CANNOT_DELETE_MESSAGE);
    }

    private static Stream<Arguments> provideQuestionAndUserWithOthersAnswer() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(A2);
        return Stream.of(
                Arguments.of(question, UserTest.JAVAJIGI)
        );
    }

    @DisplayName("질문을 삭제할 때, 답변 또한 삭제. 답변의 삭제 또한 삭제 상태(deleted)를 변경")
    @ParameterizedTest
    @MethodSource(value = "provideQuestionAndUserWithSelfAnswer")
    void assert_answer_deleted(Question question, User loginUser) throws CannotDeleteException {
        List<Answer> answers = question.getAnswers().value();

        assertThat(answers.size()).isGreaterThan(0);
        answers.forEach(answer -> assertThat(answer.isDeleted()).isFalse());
        question.delete(loginUser);

        answers.forEach(answer -> assertThat(answer.isDeleted()).isTrue());
    }

    @DisplayName("deleteHistories 생성")
    @ParameterizedTest
    @MethodSource(value = "provideQuestionWithSelfAnswer")
    void makeDeleteHistories(Question question) throws CannotDeleteException {
        assertThat(question.isDeleted()).isFalse();

        question.delete(UserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();

        List<DeleteHistory> deleteHistories = question.makeDeleteHistories();
        assertThat(deleteHistories).hasSize(3);
    }

    @DisplayName("질문이 삭제되지 않아 deleteHistories 생성 실패")
    @ParameterizedTest
    @MethodSource(value = "provideQuestionWithSelfAnswer")
    void makeDeleteHistories_fail(Question question) {
        assertThat(question.isDeleted()).isFalse();
        assertThatThrownBy(() -> question.makeDeleteHistories())
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(Question.CANNOT_MAKE_DELETE_HISTORIES_MESSAGE);
    }

    private static Stream<Arguments> provideQuestionWithSelfAnswer() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "Answers Contents1"));
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "Answers Contents2"));
        return Stream.of(
                Arguments.of(question, UserTest.JAVAJIGI)
        );
    }
}
