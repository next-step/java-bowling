package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;
import qna.exception.NotFoundException;
import qna.exception.UnAuthorizedException;

import static org.junit.jupiter.api.Assertions.assertAll;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변의 작성자가 아닐 경우 답변을 제거할 수 없다는 예외를 발생시킨다")
    void deleteException() throws Exception {
        Assertions.assertThatThrownBy(() -> {
            A1.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변이 삭제되면 삭제 상태(deleted)가 변경되어야 한다")
    void deleteFlag() throws Exception {
        Assertions.assertThat(A1.isDeleted()).isFalse();

        // when
        A1.delete(UserTest.JAVAJIGI);

        // then
        Assertions.assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("객체 생성시 null이 입력되면 정해진 예외를 발생시킨다")
    void initNull() throws Exception {
        assertAll(
                () -> Assertions.assertThatThrownBy(() -> {
                    new Answer(null, QuestionTest.Q1, "Answers Contents");
                }).isInstanceOf(UnAuthorizedException.class),

                () -> Assertions.assertThatThrownBy(() -> {
                    new Answer(UserTest.JAVAJIGI, null, "Answers Contents");
                }).isInstanceOf(NotFoundException.class)
        );
    }
}
