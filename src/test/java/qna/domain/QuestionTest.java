package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;
import qna.domain.mock.TestQuestion;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @ParameterizedTest
    @MethodSource("question")
    public void delete_성공(TestQuestion question, User loginUser) throws CannotDeleteException {
        question.writeBy(loginUser);

        assertAll(
                () -> assertThatCode(() -> question.delete(loginUser))
                        .doesNotThrowAnyException(),
                () -> assertThat(question.isDeleted()).isTrue()
        );
    }

    private static Stream<Arguments> question() {
        return Stream.of(
                Arguments.of(new TestQuestion(Q1), UserTest.JAVAJIGI),
                Arguments.of(new TestQuestion(Q2), UserTest.SANJIGI)
        );
    }

    @ParameterizedTest
    @MethodSource("questionHasOtherOwnerAnswer")
    public void delete_다른_사람이_쓴_글(Question question, User loginUser) throws CannotDeleteException {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> question.delete(loginUser))
                .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    private static Stream<Arguments> questionHasOtherOwnerAnswer() {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A2);

        Q2.addAnswer(AnswerTest.A1);
        Q2.addAnswer(AnswerTest.A2);

        return Stream.of(
                Arguments.of(Q1, UserTest.JAVAJIGI),
                Arguments.of(Q2, UserTest.SANJIGI)
        );
    }

    @ParameterizedTest
    @MethodSource("questionSameOwnerAnswer")
    public void delete_성공_질문자_답변자_같음(TestQuestion testQuestion, User loginUser) throws CannotDeleteException {
        assertAll(
                () -> assertThatCode(() -> testQuestion.delete(loginUser))
                        .doesNotThrowAnyException(),
                () -> assertThat(testQuestion.isDeleted()).isTrue()
        );
    }

    private static Stream<Arguments> questionSameOwnerAnswer() {
        TestQuestion testQuestion1 = new TestQuestion(Q1);
        testQuestion1.addAnswer(AnswerTest.A1);

        TestQuestion testQuestion2 = new TestQuestion(Q2);
        testQuestion2.addAnswer(AnswerTest.A2);

        return Stream.of(
                Arguments.of(testQuestion1, UserTest.JAVAJIGI),
                Arguments.of(testQuestion2, UserTest.SANJIGI)
        );
    }

    @ParameterizedTest
    @MethodSource("questionOtherOwner")
    public void delete_답변_중_다른_사람이_쓴_글() throws CannotDeleteException {
        User invalidLoginUser = UserTest.SANJIGI;
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A2);

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Q1.delete(invalidLoginUser))
                .withMessage("질문을 삭제할 권한이 없습니다.");
    }

    private static Stream<Arguments> questionOtherOwner() {
        return Stream.of(
                Arguments.of(Q1, UserTest.SANJIGI),
                Arguments.of(Q2, UserTest.JAVAJIGI)
        );
    }

    @ParameterizedTest
    @MethodSource("deleteHistories")
    @DisplayName("반환된 deleteHistories 중 Question DeleteHistory가 포함된다.")
    public void deleteHistoriesTest(Question question, User loginUser) {
        DeleteHistories deleteHistories = question.delete(loginUser);
        assertIterableEquals(deleteHistories, Arrays.asList(new DeleteHistory(question, LocalDateTime.now())));
    }

    private static Stream<Arguments> deleteHistories() {
        return Stream.of(
                Arguments.of(Q1, UserTest.JAVAJIGI),
                Arguments.of(Q2, UserTest.SANJIGI)
        );
    }
}
