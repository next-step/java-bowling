package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static qna.domain.QuestionTest.Q1;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, Q1, "Answers Contents2");

    @Test
    void 다른_사람이_쓴_답변이_있는_경우() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void 정상적으로_삭제되면_isDeleted_상태_변경() throws CannotDeleteException {
        DeleteHistory deleteHistories = A2.delete(UserTest.SANJIGI);
        assertThat(A2.isDeleted()).isTrue();
        assertThat(deleteHistories).isEqualTo(new DeleteHistory(ContentType.ANSWER, A2.getId(), UserTest.SANJIGI, LocalDateTime.now()));
    }
}
