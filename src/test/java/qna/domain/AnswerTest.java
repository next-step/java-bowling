package qna.domain;

import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void deleteTest() throws CannotDeleteException {
        A1.delete(UserTest.JAVAJIGI);
        A2.delete(UserTest.SANJIGI);

        assertTrue(A1.isDeleted());
        assertTrue(A2.isDeleted());

        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> A1.delete(UserTest.SANJIGI));

        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> A2.delete(UserTest.JAVAJIGI));
    }
}
