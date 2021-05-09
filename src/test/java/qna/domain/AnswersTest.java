package qna.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswersTest {

    @Test
    void 답변자_질문자같으면_삭제가능() {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1));

        List<DeleteHistory> deleteHistories = assertDoesNotThrow(answers::delete);
        assertTrue(AnswerTest.A1.isDeleted());
        assertEquals(1, deleteHistories.size());
    }

    @Test
    void 답변자_질문자다르면_삭제불가능() {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));

        assertThrows(CannotDeleteException.class,answers::delete);
    }
}
