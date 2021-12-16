package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    private static final Answer JAVAJIGI_A1 = AnswerTest.A1;
    private static final Answer SANJIGI_A2 = AnswerTest.A2;

    @Test
    @DisplayName("Answers 객체 생성")
    void create() {
        Answers answers = new Answers(Arrays.asList(JAVAJIGI_A1, SANJIGI_A2));
        assertThat(answers).isEqualTo(new Answers(Arrays.asList(JAVAJIGI_A1, SANJIGI_A2)));
    }

    @ParameterizedTest
    @MethodSource("provideUserForIsException")
    @DisplayName("다른 사람이 쓴 답변이 있음 -> CannotDeleteException 반환")
    void checkAnswersException(User loginUser) {
        Answers answers = new Answers(Arrays.asList(JAVAJIGI_A1, SANJIGI_A2));
        assertThatThrownBy(() -> answers.checkExistentAnswers(loginUser))
                .isInstanceOf(CannotDeleteException.class);
    }

    private static Stream<Arguments> provideUserForIsException() {
        return Stream.of(
                Arguments.of(UserTest.JAVAJIGI),
                Arguments.of(UserTest.SANJIGI)
        );
    }

    @ParameterizedTest
    @MethodSource("provideUserForIsOk")
    @DisplayName("다른 사람이 쓴 답변이 없음, 자기가 쓴 글 -> Validation 통과")
    void checkAnswersOK(Answer answer, User loginUser) {
        Answers answers = new Answers(answer);
        Assertions.assertThatCode(() -> answers.checkExistentAnswers(loginUser))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> provideUserForIsOk() {
        return Stream.of(
                Arguments.of(JAVAJIGI_A1, UserTest.JAVAJIGI),
                Arguments.of(SANJIGI_A2, UserTest.SANJIGI)
        );
    }

    @Test
    @DisplayName("DeleteHistories 에 댓글 삭제 내역 저장")
    void saveHistory() {
        DeleteHistories histories = new DeleteHistories();
        Answers answers = new Answers(Arrays.asList(JAVAJIGI_A1, SANJIGI_A2));
        answers.saveAtDeleteHistories(histories);

        assertThat(histories.getHistories()).size().isEqualTo(2);
        assertThat(histories.getHistories()).contains(new DeleteHistory(ContentType.ANSWER, JAVAJIGI_A1.getId(), JAVAJIGI_A1.getWriter(), LocalDateTime.now()));
        assertThat(histories.getHistories()).contains(new DeleteHistory(ContentType.ANSWER, SANJIGI_A2.getId(), SANJIGI_A2.getWriter(), LocalDateTime.now()));
    }

}
