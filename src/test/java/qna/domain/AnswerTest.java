package qna.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void delete() {
        A1.delete();

        assertTrue(A1.isDeleted());
    }

    @Test
    void writeHistory() {
        DeleteHistory deleteHistory = A1.writeHistory();

        assertEquals(A1.getId(), deleteHistory.getContentId());
    }
}
