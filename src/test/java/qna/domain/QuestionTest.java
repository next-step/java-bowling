package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("질문 테스트")
public class QuestionTest {

    @DisplayName("다른 유저의 질문을 삭제하려고 하면 예외가 발생한다.")
    @Test
    void deleteOtherUserQuestionExceptionTest() {
        // given
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);

        // when, then
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> question.deleteAndGenerateHistories(UserTest.SANJIGI, 1L))
                .withMessage("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("자신의 질문을 삭제할 때, 답변자 중 다른 유저가 있으면 예외가 발생한다.")
    @Test
    void deleteMyQuestionAndOtherUserAnswerExceptionTest() {
        // given
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(1L, UserTest.SANJIGI, question, "Answers Contents2");
        question.addAnswer(answer);

        // when, then
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() ->  question.deleteAndGenerateHistories(UserTest.JAVAJIGI, 1L))
                .withMessage("답변을 삭제할 권한이 없습니다.");
    }

    @DisplayName("답변이 없는 내 질문은 정상적으로 삭제된다.")
    @Test
    void deleteMyQuestionTest() {
        // given
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);

        // when
        question.deleteAndGenerateHistories(UserTest.JAVAJIGI, 1L);

        // then
        assertTrue(question.isDeleted());
    }

    @DisplayName("질문자와 답변자가 모두 자신이면 질문과 답변은 정상적으로 삭제된다.")
    @Test
    void deleteMyQuestionAndAnswersTest() {
        // given
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(1L, UserTest.JAVAJIGI, question, "Answers Contents1");
        question.addAnswer(answer);

        // when
        question.deleteAndGenerateHistories(UserTest.JAVAJIGI, 1L);

        // then
        assertTrue(question.isDeleted());
        assertTrue(answer.isDeleted());
    }
}
