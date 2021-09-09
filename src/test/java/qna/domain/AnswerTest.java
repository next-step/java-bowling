package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변자가 아닌 경우 답변을 삭제할 수 없다.")
    @Test
    public void answerDeletePermissionTest() {
        assertThatThrownBy(() -> A1.deleteBy(UserTest.SANJIGI))
                .isExactlyInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("답변 삭제 시, 답변은 삭제 상태가 된다.")
    @Test
    public void deletedAnswerStatusTest() {
        assertThat(A1.isDeleted()).isFalse();
        A1.deleteBy(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

}
