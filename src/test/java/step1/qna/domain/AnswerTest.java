package step1.qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step1.qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class AnswerTest {
    public Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q2, "Answers Contents2");

    @Test
    @DisplayName("다른 사람이 쓴 답변은 지울 수 없어야 한다.")
    void checkDeleteCondition() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> A2.delete(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("삭제된 후에 제대로 기록이 처리되는지 확인")
    void deleteAndRecord() throws CannotDeleteException {
        assertFalse(A1.isDeleted());
        DeleteHistory deleteHistory1 = A1.delete(UserTest.JAVAJIGI);
        assertTrue(A1.isDeleted());
        assertEquals(deleteHistory1.getDeletedBy(), UserTest.JAVAJIGI);

        assertFalse(A2.isDeleted());
        DeleteHistory deleteHistory2 = A2.delete(UserTest.SANJIGI);
        assertTrue(A2.isDeleted());
        assertEquals(deleteHistory2.getDeletedBy(), UserTest.SANJIGI);
    }
}
