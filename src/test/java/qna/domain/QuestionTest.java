package qna.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void 로그인사용자와_질문한_사람이_다르면_삭제가능() {
        List<DeleteHistory> deleteHistories = assertDoesNotThrow(
            () -> Q1.delete(UserTest.JAVAJIGI));
        assertTrue(Q1.isDeleted());
        assertEquals(1, deleteHistories.size());
    }

    @Test
    void 로그인사용자와_질문한_사람이_다르면_삭제불가() {
        assertThrows(CannotDeleteException.class, () -> Q1.delete(UserTest.SANJIGI));
    }

    @Test
    void 답변자_질문자같으면_삭제가능() {
        Q1.addAnswer(AnswerTest.A1);
        List<DeleteHistory> deleteHistories = assertDoesNotThrow(
            () -> Q1.delete(UserTest.JAVAJIGI));
        assertTrue(Q1.isDeleted());
        assertEquals(2, deleteHistories.size());
    }
}
