package qna.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 질문자와_답변자가_같으면_삭제가능() {
        DeleteHistory deleteHistory = assertDoesNotThrow(A1::delete);
        assertTrue(A1.isDeleted());
        assertNotNull(deleteHistory);
    }

    @Test
    void 질문자와_답변자가_다르면_삭제불가(){
        assertThrows(CannotDeleteException.class,A2::delete);
    }
}
