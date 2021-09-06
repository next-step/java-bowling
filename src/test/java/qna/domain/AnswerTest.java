package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("답변 테스트")
public class AnswerTest {

    @DisplayName("다른 유저의 답변을 삭제하려고 하면 예외가 발생한다.")
    @Test
    void deleteOtherUserAnswerExceptionTest() {
        // given
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(1L, UserTest.SANJIGI, question, "Answers Contents1");

        // when, then
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> answer.deleteAndGenerateHistory(UserTest.JAVAJIGI))
                .withMessage("답변을 삭제할 권한이 없습니다.");
    }

    @DisplayName("내 답변을 삭제하면 정상적으로 삭제된다.")
    @Test
    void deleteMyAnswerTest() {
        // given
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(1L, UserTest.SANJIGI, question, "Answers Contents1");

        // when
        answer.deleteAndGenerateHistory(UserTest.SANJIGI);

        // then
        assertTrue(answer.isDeleted());
    }
}
