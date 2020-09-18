package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @Test
    public void 본인의_답변일_경우_삭제() throws Exception {
        Q1.delete(UserTest.JAVAJIGI);
        assertTrue(Q1.isDeleted());

        Q2.delete(UserTest.SANJIGI);
        assertTrue(Q2.isDeleted());;
    }

    @Test
    public void 타인의_답변일_경우_삭제() throws Exception {
        assertThrows(CannotDeleteException.class, () -> Q1.delete(UserTest.SANJIGI));
        assertThrows(CannotDeleteException.class, () -> Q2.delete(UserTest.JAVAJIGI));
    }
}
