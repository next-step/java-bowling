package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("Answer delete check")
    void delete() throws CannotDeleteException {
        A1.delete(UserTest.JAVAJIGI);
        assertTrue(A1.isDeleted());
    }

    @Test
    @DisplayName("delete validate user and throw exception")
    void validate(){
        assertThrows(CannotDeleteException.class, () -> A1.delete(UserTest.SANJIGI));
    }
}
