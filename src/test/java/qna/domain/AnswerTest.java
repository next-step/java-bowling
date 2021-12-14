package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, "Answers Contents2");

    @ParameterizedTest
    @DisplayName("본인이 적은 답변을 삭제할 수 있다")
    @MethodSource
    void delete(Answer answer, User loginUser) throws CannotDeleteException {
        answer.delete(loginUser);

        assertThat(answer.isDeleted()).isTrue();
    }

    static Stream<Arguments> delete() {
        return Stream.of(
                Arguments.of(
                        A1, UserTest.JAVAJIGI
                ),
                Arguments.of(
                        A2, UserTest.SANJIGI
                )
        );
    }

    @ParameterizedTest
    @DisplayName("본인이 작성한 답변이 아니라면 예외")
    @MethodSource
    void deleteException(Answer answer, User loginUSer) {
        assertThatThrownBy(() -> answer.delete(loginUSer)).isInstanceOf(CannotDeleteException.class);
    }

    static Stream<Arguments> deleteException() {
        return Stream.of(
                Arguments.of(
                        A1, UserTest.SANJIGI
                ),
                Arguments.of(
                        A2, UserTest.JAVAJIGI
                )
        );
    }

    @ParameterizedTest
    @DisplayName("DeleteHistory를 생성한다")
    @MethodSource
    void createDeleteHistory(Answer answer) {
        assertThat(answer.createDeleteHistory()).isInstanceOf(DeleteHistory.class);
    }

    static Stream<Arguments> createDeleteHistory() {
        return Stream.of(
                Arguments.of(
                        A1
                ),
                Arguments.of(
                        A2
                )
        );
    }

}
