package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;

import static org.junit.jupiter.api.Assertions.*;


public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변자가 같은 경우 삭제 성공")
    void test_delete_success() throws CannotDeleteException{
        // When
        A1.delete(UserTest.JAVAJIGI);

        // Then
        assertTrue(A1.isDeleted());
    }

    @Test
    @DisplayName("답변자가 다른 경우 삭제 실패")
    void test_delete_fail() {
        assertThrows(CannotDeleteException.class, () -> A1.delete(UserTest.SANJIGI));
    }
}
