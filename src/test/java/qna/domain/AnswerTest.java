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

    @Test
    @DisplayName("다른 사람이 작성한 답변을 지우면 CannotDeleteException 발생")
    void throwCannotDeleteExceptionTest() {
        assertThatThrownBy(() -> A1.deleteAnswer(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("본인이 작성한 답변을 지우면 delete history 생성")
    void deleteAnswerTest() throws CannotDeleteException {
        DeleteHistory deleteHistory = A2.deleteAnswer(UserTest.SANJIGI);
        assertAll(
                () -> assertThat(deleteHistory).isInstanceOf(DeleteHistory.class),
                () -> assertThat(A2.isDeleted()).isTrue()
        );
    }
}
