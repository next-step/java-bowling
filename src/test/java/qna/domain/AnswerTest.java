package qna.domain;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.QuestionTest.Q1;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

public class AnswerTest {
    public static final Answer A1 = new Answer(1L, JAVAJIGI, Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(2L, SANJIGI, Q1, "Answers Contents2");

    @Test
    @DisplayName("답변 삭제 - 삭제 권한 없는 경우")
    public void 답변삭제_삭제권한없음() {
        // given
        String message = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

        // when
        ThrowingCallable throwingCallable = () -> A1.delete(SANJIGI);

        // then
        assertThatThrownBy(throwingCallable)
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(message);
    }

    @Test
    @DisplayName("답변 삭제 - 권한 있는 경우")
    public void 답변삭제_삭제권한있음() {
        // given
        DeleteHistory expectedDeleteHistory = DeleteHistory.answerOf(A1.getId(), JAVAJIGI);

        // when
        DeleteHistory delete = A1.delete(JAVAJIGI);

        // then
        assertThat(delete).isEqualTo(expectedDeleteHistory);
    }

}
