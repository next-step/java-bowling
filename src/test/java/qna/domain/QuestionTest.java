package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.domain.questions.Question;
import qna.exception.CannotDeleteException;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("생성 성공")
    void create() {
        assertTrue(Q1.equals(Q1));
        assertFalse(Q1.equals(Q2));
    }

    @Test
    @DisplayName("삭제 성공")
    void delete() {
        assertDoesNotThrow(() -> Q1.delete(UserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("삭제 에러 - 자신의 게시물이 아닐 경우")
    void delete_exception() {
        assertThrows(CannotDeleteException.class, () -> Q1.delete(UserTest.SANJIGI));
    }
}
