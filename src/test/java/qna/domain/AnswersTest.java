package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AnswersTest {

    private Answers answers1 = new Answers(Arrays.asList(AnswerTest.JAVAJIGI_ANSWER));
    private Answers answers2 = new Answers(Arrays.asList(AnswerTest.JAVAJIGI_ANSWER, AnswerTest.SANJIGI_ANSWER));

    @Test
    @DisplayName("생성")
    void create() {
        assertEquals(answers1, answers1);
    }

    @Test
    @DisplayName("자신의 답변만 삭제 가능")
    void canOnlyDeleteOwnAnswers() {
        assertDoesNotThrow(() -> answers1.delete(UserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("자신이 작성한 답변이 아닐경우 삭제 불가능")
    void ifTheAnswerIsNotYourOwnItCannotBeDeleted() {
        assertThrows(CannotDeleteException.class, () -> answers2.delete(UserTest.JAVAJIGI));
    }


}