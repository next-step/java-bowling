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

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1")
            .writeBy(UserTest.JAVAJIGI);

    public static final Question Q2 = new Question("title2", "contents2")
            .writeBy(UserTest.SANJIGI);

    @ParameterizedTest
    @MethodSource
    void addAnswer(Question question, Answer answer, int expectedSize) {
        question.addAnswer(answer);

        assertThat(question.answersCount()).isEqualTo(expectedSize);
    }

    static Stream<Arguments> addAnswer() {
        return Stream.of(
                Arguments.of(
                        Q1, new Answer(UserTest.JAVAJIGI, "Answers Contents1"), 1
                ),
                Arguments.of(
                        Q1, new Answer(UserTest.JAVAJIGI, "Answers Contents1"), 2
                )
                ,
                Arguments.of(
                        Q2, new Answer(UserTest.JAVAJIGI, "Answers Contents1"), 1
                )
        );
    }

    @ParameterizedTest
    @DisplayName("작성자는 글을 삭제할 수 있다")
    @MethodSource
    void deleteQuestion(User loginUser, Question question) {
        question.deleteQuestion(loginUser);

        assertThat(question.isDeleted()).isTrue();
    }

    static Stream<Arguments> deleteQuestion() {
        return Stream.of(
                Arguments.of(
                    UserTest.JAVAJIGI, Q1
                ),
                Arguments.of(
                    UserTest.SANJIGI, Q2
                )
        );
    }

    @ParameterizedTest
    @DisplayName("작성자가 아닌 사람이 글 삭제시 예외가 발생한다")
    @MethodSource
    void deleteException(User loginUser, Question question) {
        question.deleteQuestion(loginUser);

        assertThatThrownBy(question::isDeleted).isInstanceOf(CannotDeleteException.class);
    }

    static Stream<Arguments> deleteException() {
        return Stream.of(
                Arguments.of(
                    UserTest.SANJIGI, Q1
                ),
                Arguments.of(
                    UserTest.JAVAJIGI, Q2
                )
        );
    }

    @ParameterizedTest
    @DisplayName("삭제된 질문은 질문에 포함된 댓글과 함께 DeleteHistory를 생성할수 있다")
    @MethodSource
    void createDeleteHistory(Question question, User user, int size) {
        question.deleteQuestion(user);
        List<DeleteHistory> deleteHistories = question.createDeleteHistories();

        assertThat(deleteHistories.size()).isEqualTo(size);
    }

    static Stream<Arguments> createDeleteHistory() {
        Q1.addAnswer(new Answer(UserTest.JAVAJIGI, "contents"));
        Q1.addAnswer(new Answer(UserTest.JAVAJIGI, "contents"));
        Q2.addAnswer(new Answer(UserTest.SANJIGI, "contents"));

        return Stream.of(
                Arguments.of(
                    Q1, UserTest.JAVAJIGI, 3
                ),
                Arguments.of(
                    Q2, UserTest.SANJIGI, 2
                )
        );
    }

    @ParameterizedTest
    @DisplayName("질문이 삭제된 상태가 아니라면 DeleteHistory를 생성하지 못한다")
    @MethodSource
    void createDeleteHistoryException(Question question) {
        assertThatThrownBy(question::createDeleteHistories).isInstanceOf(FaildCreateDeleteHistoryException.class);
    }

    static Stream<Arguments> createDeleteHistoryException() {
        return Stream.of(
                Arguments.of(
                        Q1
                ),
                Arguments.of(
                        Q2
                )
        );
    }
}
