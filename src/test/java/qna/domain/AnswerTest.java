package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;


public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("다른 사람이 작성한 답변을 삭제하면 예외 발생테스트")
    @Test
    void answerDeleteExceptionTest() throws CannotDeleteException {
        assertThatThrownBy(
                () -> A1.delete(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("답변 삭제 테스트")
    @Test
    void answerDeleteTest() throws CannotDeleteException {
        DeleteHistory deleteHistory = A1.delete(UserTest.JAVAJIGI);
        assertAll(
                ()->assertThat(deleteHistory).isNotNull(),
                ()->assertThat(A1.isDeleted()).isTrue()
        );
    }
}
