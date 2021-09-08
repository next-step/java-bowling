package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("사용자!=답변자: 삭제 불가")
    @Test
    public void delete_answer_exist_from_other_user() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("사용자=답변자: 삭제 가능")
    @Test
    public void delete_answer_made_by_oneself() {
        A1.delete(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @DisplayName("삭제시 기록을 남긴다.")
    @Test
    public void delete_leave_history() {
        assertThat(A1.delete(UserTest.JAVAJIGI)).isInstanceOf(DeleteHistory.class);
    }
}
