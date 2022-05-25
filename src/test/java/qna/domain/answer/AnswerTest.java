package qna.domain.answer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.domain.question.QuestionTest;
import qna.domain.user.UserTest;
import qna.exception.CannotDeleteException;
import qna.exception.IsNotDeletedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("질문과 답변 작성자가 동일한 경우 삭제 가능")
    void delete() {
        A1.delete(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문과 답변 작성자가 동일하지 않은 경우 삭제 불가능")
    void answerIsNotEqualQuestion() {
        assertThatThrownBy(() -> A2.delete(UserTest.JAVAJIGI)).isExactlyInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("삭제되지 않은 질문의 삭제 기록 조회 시 예외 처리")
    void deleteHistoryException() {
        assertThatThrownBy(A2::deleteHistory).isExactlyInstanceOf(IsNotDeletedException.class);
    }
}
