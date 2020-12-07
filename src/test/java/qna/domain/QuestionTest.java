package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("질문 작성자가 같으면 삭제 성공")
    void test_delete_success() throws CannotDeleteException {
        // When
        Q1.delete(UserTest.JAVAJIGI);

        // Then
        assertTrue(Q1.isDeleted());
    }

    @Test
    @DisplayName("질문 작성자가 다르면 삭제 실패")
    void test_delete_fail() {
        assertThrows(CannotDeleteException.class, () -> Q1.delete(UserTest.SANJIGI));
    }
}
