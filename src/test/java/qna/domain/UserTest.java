package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @DisplayName("Question 삭제 권한 테스트")
    @Test
    public void checkDeletionQuestionPermissionTest() {
        assertTrue(JAVAJIGI.canDelete(QuestionTest.Q1));
        assertFalse(JAVAJIGI.canDelete(QuestionTest.Q2));
    }

    @DisplayName("Answer 삭제 권한 테스트")
    @Test
    public void checkDeletionAnswerPermissionTest() {
        assertTrue(JAVAJIGI.canDelete(AnswerTest.A1));
        assertFalse(JAVAJIGI.canDelete(AnswerTest.A2));
    }
}
