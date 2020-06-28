package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.UnAuthorizedException;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnswerTest {

    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("Answer 생성자 테스트")
    @ParameterizedTest
    @MethodSource("provideAnswer")
    void answerConstructorTest(final long id, final User user, final Question question, final String content) {
        Answer answer = new Answer(id, user, question, content);

        assertThat(answer.getWriter()).isEqualTo(user);
        assertThat(answer.getQuestion()).isEqualTo(question);
        assertThat(answer.getContents()).isEqualTo(content);
    }

    private static Stream<Arguments> provideAnswer() {
        return Stream.of(
                Arguments.of(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "abc"),
                Arguments.of(2L, UserTest.SANJIGI, QuestionTest.Q2, "efg")
        );
    }

    @DisplayName("Answer 생성자 예외 테스트 :: UnAuthorizedException")
    @ParameterizedTest
    @MethodSource("provideUnAuthorizedExceptionAnswer")
    void answerConstructorUnAuthorizedExceptionTest(final long id, final User user, final Question question, final String content) {
        assertThatExceptionOfType(UnAuthorizedException.class)
                .isThrownBy(() -> new Answer(id, user, question, content));
    }

    private static Stream<Arguments> provideUnAuthorizedExceptionAnswer() {
        return Stream.of(
                Arguments.of(1L, null, QuestionTest.Q1, "abc")
        );
    }

    @DisplayName("Answer 생성자 예외 테스트 :: NotFoundException")
    @ParameterizedTest
    @MethodSource("provideNotFoundExceptionAnswer")
    void answerConstructorNotFoundExceptionTest(final long id, final User user, final Question question, final String content) {
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> new Answer(id, user, question, content));
    }

    private static Stream<Arguments> provideNotFoundExceptionAnswer() {
        return Stream.of(
                Arguments.of(1L, UserTest.SANJIGI, null, "abc")
        );
    }

    @DisplayName("Answer deleteAnswer() 메소드 테스트")
    @ParameterizedTest
    @MethodSource("provideAnswerForDelete")
    void deleteAnswerTest(final long id, final User user, final Question question, final String content, final DeleteHistory expected) throws CannotDeleteException {
        Answer answer = new Answer(id, user, question, content);
        DeleteHistory deleteHistory = answer.deleteAnswer(user);

        assertThat(answer.isDeleted()).isTrue();
        assertThat(deleteHistory).isEqualTo(expected);
    }

    private static Stream<Arguments> provideAnswerForDelete() {
        return Stream.of(
                Arguments.of(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "abc", DeleteHistory.of(ContentType.ANSWER, 1L, UserTest.JAVAJIGI, LocalDateTime.now())),
                Arguments.of(2L, UserTest.SANJIGI, QuestionTest.Q2, "efg", DeleteHistory.of(ContentType.ANSWER, 2L, UserTest.SANJIGI, LocalDateTime.now()))
        );
    }
}
