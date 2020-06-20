package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @MethodSource("provideQuestionByGuest")
    @ParameterizedTest
    @DisplayName("질문 삭제 테스트 - 이미 삭제 된 경우")
    void isDeletedQuestionDelete(Question question) throws CannotDeleteException {
        question.delete(User.GUEST_USER);
        List<DeleteHistory> deleteHistoryList = question.delete(User.GUEST_USER);

        assertThat(deleteHistoryList).isEmpty();
        assertThat(deleteHistoryList).hasSize(0);
    }

    @MethodSource("provideQuestionByGuest")
    @ParameterizedTest
    @DisplayName("질문 삭제 테스트 - 답변이 없는 경우")
    void deleteWithNoAnswers(Question question) throws CannotDeleteException {

        List<DeleteHistory> deleteHistoryList = question.delete(User.GUEST_USER);

        assertThat(deleteHistoryList).isNotEmpty();
        assertThat(deleteHistoryList).hasSize(1);
    }

    @MethodSource("provideQuestionByGuest")
    @ParameterizedTest
    @DisplayName("질문 삭제 테스트 - 삭제하려고하는 유저가 질문자가 아닌 경우")
    void deleteByAnotherUser(Question question) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> question.delete(UserTest.SANJIGI));
    }

    @MethodSource("provideQuestionByGuest")
    @ParameterizedTest
    @DisplayName("질문 삭제 테스트 - 답변의 모든 유저가 질문자와 같은 경우")
    void deleteWithEqualsAllAnswerUser(Question question) throws CannotDeleteException {

        question.addAnswer(new Answer(User.GUEST_USER, question, "답변1"));
        question.addAnswer(new Answer(User.GUEST_USER, question, "답변2"));
        question.addAnswer(new Answer(User.GUEST_USER, question, "답변3"));

        List<DeleteHistory> deleteHistoryList = question.delete(User.GUEST_USER);

        assertThat(deleteHistoryList).isNotEmpty();
        assertThat(deleteHistoryList).hasSize(4);

    }

    @MethodSource("provideQuestionByGuest")
    @ParameterizedTest
    @DisplayName("질문 삭제 테스트 - 답변의 모든 유저가 질문자와 같지 경우")
    void deleteWithEqualsAllAnswerUserException(Question question) {

        question.addAnswer(new Answer(User.GUEST_USER, question, "답변1"));
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "답변2"));
        question.addAnswer(new Answer(User.GUEST_USER, question, "답변3"));

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> question.delete(User.GUEST_USER));

    }

    private static Stream<Arguments> provideQuestionByGuest() {
        return Stream.of(
                Arguments.of(new Question("title2", "contents2").writeBy(User.GUEST_USER))
        );
    }
}
