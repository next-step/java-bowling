package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("삭제 상태로 변경후 반환")
    @Test
    public void setDeleted_success() throws Exception {
        //given
        Answer answer = A1.deleted(true);

        //then
        assertFalse(A1.isDeleted());
        assertTrue(answer.isDeleted());
    }

    @DisplayName("삭제 가능한 유저인지 체크")
    @Test
    public void validateDeleteAble() throws Exception {
        A1.validateDeleteAble(UserTest.JAVAJIGI);
    }

    @DisplayName("삭제 가능한 유저 아닐경우 체크")
    @Test
    public void validateDeleteAble_fail() throws Exception {
        assertThatThrownBy(
                () -> A1.validateDeleteAble(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
