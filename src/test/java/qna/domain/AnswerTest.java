package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변 삭제 예외 테스트")
    @Test
    void shouldExceptionWriterNotEqualsLoginUser() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> A1.delete(UserTest.SANJIGI))
                .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("답변 삭제 테스트")
    @Test
    void delete() throws CannotDeleteException {
        A2.delete(UserTest.SANJIGI);
        assertThat(A2.isDeleted()).isTrue();
        assertThat(A1.isDeleted()).isFalse();
    }
}
