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
    void 질문자와_답변한_사람이_다를_경우_삭제할_수_없다() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> A2.delete(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 답변을_삭제하면_상태가_삭제상태로_변경된다() throws CannotDeleteException {
        A1.delete(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    void 답변_삭제_이력을_남긴다() throws CannotDeleteException {
        DeleteHistory deleteHistory = A1.delete(UserTest.JAVAJIGI);
        DeleteHistory otherDeleteHistory = new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now());

        assertThat(deleteHistory).isEqualTo(otherDeleteHistory);;
    }
}
