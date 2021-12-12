package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnswerTest {

    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("delete하면 setDeleted 값 true로 바뀌는 것 테스트")
    void afterDeleteIsDeletedTest() {
        assertFalse(A1.isDeleted());
        A1.delete();
        assertTrue(A1.isDeleted());
    }

    @Test
    @DisplayName("작성자가 맞다면 true 아니면 false 반환 테스트")
    void isOwnerTest() {
        assertFalse(A2.isOwner(UserTest.JAVAJIGI));
        assertTrue(A2.isOwner(UserTest.SANJIGI));
    }
}
