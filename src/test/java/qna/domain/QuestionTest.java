package qna.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("로그인사용자와 질문한 사람이 같으면 삭제가능")
    void checkPermissionEqualUser() {
        assertDoesNotThrow(() -> Q1.checkPermission(UserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("로그인사용자와 질문한 사람이 다르면 삭제불가")
    void checkPermissionNotEqualUser() {
        assertThrows(CannotDeleteException.class, () -> Q1.checkPermission(UserTest.SANJIGI));
    }

    @Test
    @DisplayName("다른 사람이 쓴 답변이 있으면 삭제불가")
    void checkOwnerAnswers() {
        assertThrows(CannotDeleteException.class, () -> Q1.checkOwnerAnswers(UserTest.SANJIGI));
    }

    @Test
    void deleted() {
        Q1.delete();

        assertTrue(Q1.isDeleted());
    }
}
