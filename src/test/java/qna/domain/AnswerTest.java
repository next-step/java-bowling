package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AnswerTest {
    private static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    private static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("answer 의 user 가 다르면, CannotDeleteException 이 발생한다.")
    void checkDeletable() {
        assertAll(
                () -> assertThatExceptionOfType(CannotDeleteException.class)
                        .isThrownBy(() -> A1.checkDeletable(UserTest.SANJIGI))
                        .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다."),
                () -> assertDoesNotThrow(() -> A1.checkDeletable(UserTest.JAVAJIGI)),
                () -> assertThatExceptionOfType(CannotDeleteException.class)
                        .isThrownBy(() -> A2.checkDeletable(UserTest.JAVAJIGI))
                        .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다."),
                () -> assertDoesNotThrow(() -> A2.checkDeletable(UserTest.SANJIGI))
        );
    }

    private boolean delete(Answer A) {
        A.delete();
        return A.isDeleted();
    }

    @Test
    @DisplayName("delete 되면 isDeleted 가 true 가 된다.")
    void delete() {
        assertAll(
                () -> assertThat(A1.isDeleted())
                        .isFalse(),
                () -> assertThat(A2.isDeleted())
                        .isFalse(),
                () -> assertThat(delete(A1))
                        .isTrue(),
                () -> assertThat(delete(A2))
                        .isTrue()
        );
    }
}
