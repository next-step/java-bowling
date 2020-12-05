package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.junit.jupiter.api.Assertions.*;

class AnswersTest {

    Answers answers = new Answers();

    @Test
    @DisplayName("모두 나의 답변일 경우 삭제가 가능하다.")
    void test_delete_success() throws CannotDeleteException {
        // Given
        answers.addAnswer(AnswerTest.A1);

        // When
        answers.deleteAll(UserTest.JAVAJIGI);

        // Then
        assertTrue(AnswerTest.A1.isDeleted());
    }

    @Test
    @DisplayName("다른 사람의 답변이 있을 경우 삭제할 수 없다.")
    void test_delete_fail() {
        // Given
        answers.addAnswer(AnswerTest.A1);
        answers.addAnswer(AnswerTest.A2);

        // When
        assertThrows(CannotDeleteException.class, () -> answers.deleteAll(UserTest.JAVAJIGI));

    }

    @Test
    @DisplayName("다른 사람의 답변이 있는지 확인한다.")
    void test_hasOtherOwnedAnswer_true() {
        // Given
        answers.addAnswer(AnswerTest.A1);
        answers.addAnswer(AnswerTest.A2);

        // When & Then
        assertTrue(answers.hasOtherOwnedAnswer(UserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("다른 사람의 답변이 있는지 확인한다.")
    void test_hasOtherOwnedAnswer_false() {
        // Given
        answers.addAnswer(AnswerTest.A1);

        // When & Then
        assertFalse(answers.hasOtherOwnedAnswer(UserTest.JAVAJIGI));
    }

}