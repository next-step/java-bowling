package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("다른 사람이 쓴 답변일 경우 삭제 시 예외")
    void testTryDeleteOtherUser() {
        assertThatThrownBy(() -> A1.deleteByUser(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("작성자가 쓴 답변일 경우 삭제")
    void testTryDeleteWriteUser() {
        A1.deleteByUser(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }
}
