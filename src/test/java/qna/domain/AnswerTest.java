package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("Answer 삭제 처리 후 DeleteHistory 반환")
    void delete() throws CannotDeleteException {
        // when
        DeleteHistory deleteHistory = A1.delete(UserTest.JAVAJIGI);

        //then
        assertThat(A1.isDeleted()).isTrue();
        assertThat(deleteHistory)
                .isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()));
    }

    @Test
    @DisplayName("Answer 삭제 실패 : loginUser 외 댓글 작성자 여부 확인")
    void delete_fail_different_comment_user() {
        // when, then
        assertThatThrownBy(() -> A2.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
