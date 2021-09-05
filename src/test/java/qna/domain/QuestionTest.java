package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.domain.questions.Question;
import qna.exception.CannotDeleteException;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("생성 성공")
    void create() {
        assertTrue(Q1.equals(Q1));
        assertFalse(Q1.equals(Q2));
    }

    @Test
    @DisplayName("삭제 성공 - 답변이 없는 경우")
    void deleteIfThereIsNoAnswer() {
        assertDoesNotThrow(() -> question.delete(UserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("삭제 성공 - 자신의 답변만 존재할 경우")
    void deleteIfOnlyYourAnswerExists() {
        question.addAnswer(AnswerTest.JAVAJIGI_ANSWER);
        assertDoesNotThrow(() -> question.delete(UserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("삭제 에러 - 질문자와 답변자가 다른경우")
    void deleteWhenTheQuestionerAndTheAnswererAreDifferent_exception() {
        question.addAnswer(AnswerTest.SANJIGI_ANSWER);
        assertThrows(CannotDeleteException.class, () -> question.delete(UserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("삭제 에러 - 자신 이외의 답변이 존재할 경우")
    void deleteIfThereIsAnAnswerOtherThanYourself_exception() {
        question.addAnswer(AnswerTest.JAVAJIGI_ANSWER);
        question.addAnswer(AnswerTest.SANJIGI_ANSWER);
        assertThrows(CannotDeleteException.class, () -> question.delete(UserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("삭제 에러 - 자신의 게시물이 아닐 경우")
    void deleteIfItsNotYourOwnQuestion_exception() {
        assertThrows(CannotDeleteException.class, () -> question.delete(UserTest.SANJIGI));
    }
}
