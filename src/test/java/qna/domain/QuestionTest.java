package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Question 로직 테스트")
public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("Question 삭제 성공")
    @ParameterizedTest
    @MethodSource("source_delete_question_shouldSuccess")
    public void delete_question_shouldSuccess(Question question, User owner) throws CannotDeleteException {
        question.delete(owner);
        assertThat(question.isDeleted()).isTrue();
    }

    public static Stream<Arguments> source_delete_question_shouldSuccess() {
        return Stream.of(
                Arguments.of(Q1, UserTest.JAVAJIGI),
                Arguments.of(Q2, UserTest.SANJIGI)
        );
    }

    @DisplayName("Question 삭제 시에 삭제 이력이 생성된다")
    @ParameterizedTest
    @MethodSource("source_get_deleteHistory_shouldSuccess")
    public void get_deleteHistory_shouldSuccess(Question question, User owner) throws CannotDeleteException {
        DeleteHistory deleteHistory = question.delete(owner);
        assertAll(
                () -> assertThat(deleteHistory.getContentType()).isEqualTo(ContentType.QUESTION),
                () -> assertThat(deleteHistory.getContentId()).isEqualTo(question.getId()),
                () -> assertThat(deleteHistory.getDeletedBy()).isEqualTo(question.getWriter())
        );
    }

    public static Stream<Arguments> source_get_deleteHistory_shouldSuccess() {
        return Stream.of(
                Arguments.of(Q1, UserTest.JAVAJIGI),
                Arguments.of(Q2, UserTest.SANJIGI)
        );
    }

    @DisplayName("다른 사람이 쓴 Question 삭제 시에는 예외 발생")
    @ParameterizedTest
    @MethodSource("source_delete_anotherWriter_shouldFail")
    public void delete_anotherWriter_shouldFail(Question question, User owner) {
        assertThatThrownBy(() -> {
            question.delete(owner);
        }).isInstanceOf(CannotDeleteException.class);
    }

    public static Stream<Arguments> source_delete_anotherWriter_shouldFail() {
        return Stream.of(
                Arguments.of(Q1, UserTest.SANJIGI),
                Arguments.of(Q2, UserTest.JAVAJIGI)
        );
    }
}
