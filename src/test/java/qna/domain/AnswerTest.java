package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.domain.answers.Answer;

import static org.junit.jupiter.api.Assertions.*;

class AnswerTest {

    public static final Answer JAVAJIGI_ANSWER = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer SANJIGI_ANSWER = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("생성")
    void create() {
        assertTrue(JAVAJIGI_ANSWER.equals(JAVAJIGI_ANSWER));
        assertFalse(JAVAJIGI_ANSWER.equals(SANJIGI_ANSWER));
    }

    @Test
    @DisplayName("답변자 확인")
    void isOwnerCheck() {
        JAVAJIGI_ANSWER.isOwner(UserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("삭제 플래그 변경")
    void changeDeleted() {
        JAVAJIGI_ANSWER.changeDeleted(true);
        assertEquals(JAVAJIGI_ANSWER.isDeleted(), true);
    }
}
