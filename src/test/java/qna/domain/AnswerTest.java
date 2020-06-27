package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변 삭제 성공")
    @ParameterizedTest
    @MethodSource("source_delete_answer_shouldSuccess")
    public void delete_answer_shouldSuccess(Answer answer, User owner) throws CannotDeleteException {
        answer.delete(owner);
        assertThat(answer.isDeleted()).isTrue();
    }

    public static Stream<Arguments> source_delete_answer_shouldSuccess() {
        return Stream.of(
                Arguments.of(A1, UserTest.JAVAJIGI),
                Arguments.of(A2, UserTest.SANJIGI)
        );
    }

    @DisplayName("Answer 삭제 시에 삭제 이력이 생성된다")
    @ParameterizedTest
    @MethodSource("source_get_deleteHistory_shouldSuccess")
    public void get_deleteHistory_shouldSuccess(Answer answer, User owner) throws CannotDeleteException {
        DeleteHistory deleteHistory = answer.delete(owner);
        assertAll(
                () -> assertThat(deleteHistory.getContentType()).isEqualTo(ContentType.ANSWER),
                () -> assertThat(deleteHistory.getContentId()).isEqualTo(answer.getId()),
                () -> assertThat(deleteHistory.getDeletedBy()).isEqualTo(answer.getWriter())
        );
    }

    public static Stream<Arguments> source_get_deleteHistory_shouldSuccess() {
        return Stream.of(
                Arguments.of(A1, UserTest.JAVAJIGI),
                Arguments.of(A2, UserTest.SANJIGI)
        );
    }

    @DisplayName("다른 사람이 쓴 답변이 있다면 삭제 시에 예외 발생")
    @ParameterizedTest
    @MethodSource("source_delete_anotherOwner_shouldFail")
    public void delete_anotherOwner_shouldFail(Answer answer, User owner) {
        assertThatThrownBy(() -> {
            answer.delete(owner);
        }).isInstanceOf(CannotDeleteException.class);
    }

    public static Stream<Arguments> source_delete_anotherOwner_shouldFail() {
        return Stream.of(
                Arguments.of(A1, UserTest.SANJIGI),
                Arguments.of(A2, UserTest.JAVAJIGI)
        );
    }
}
