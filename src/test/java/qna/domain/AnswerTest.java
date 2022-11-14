package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 다른_사람이_쓴_답변이_있을_때() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void 정상적으로_삭제되는_경우_isDeleted_로_상태_변경() throws CannotDeleteException {
        DeleteHistory deleteHistory = A2.delete(UserTest.SANJIGI);
        assertThat(A2.isDeleted()).isEqualTo(true);
        assertThat(deleteHistory).isEqualTo(
                new DeleteHistory(
                        ContentType.ANSWER, A2.getId(), UserTest.SANJIGI, LocalDateTime.now()
                )
        );
    }
}
