package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("다른 사람이 작성한 답변이 있는 경우 삭제 불가 확인")
    @Test
    void deletable() {
        assertThatThrownBy(() -> A1.deletable(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("답변의 정상적 삭제 확인")
    @Test
    void delete() {
        assertThat(A1.isDeleted()).isFalse();
        A1.delete();
        assertThat(A1.isDeleted()).isTrue();
    }
}
