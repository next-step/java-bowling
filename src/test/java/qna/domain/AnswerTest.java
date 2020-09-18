package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @Test
    public void 본인의_답변일_경우_삭제() throws Exception {
        A1.delete(UserTest.JAVAJIGI);
        assertTrue(A1.isDeleted());

        A2.delete(UserTest.SANJIGI);
        assertTrue(A2.isDeleted());;
    }

    @Test
    public void 타인의_답변일_경우_삭제() throws Exception {
        assertThrows(CannotDeleteException.class, () -> A1.delete(UserTest.SANJIGI));
        assertThrows(CannotDeleteException.class, () -> A2.delete(UserTest.JAVAJIGI));
    }
}
