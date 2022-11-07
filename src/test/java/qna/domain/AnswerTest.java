package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

public class AnswerTest {
    public static final Answer A1 = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 작성자가_아닌_사람이_답변을_삭제할_시_예외가_발생한다() {
        assertThatThrownBy(() -> A1.delete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void 작성자가_답변을_삭제할_시_삭제_상태가_참이_된다() throws CannotDeleteException {
        A1.delete(JAVAJIGI);

        assertThat(A1).isEqualTo(new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1", true));
    }

    @Test
    void 답변_삭제_상태를_판별_할_수_있다() {
        assertFalse(A1.isNotDeleted());
    }

    @Test
    void 삭제_히스토리를_생성할_수_있다() {
        assertThat(A1.deleteHistory()).isEqualTo(new DeleteHistory(ContentType.ANSWER, JAVAJIGI));
    }
}
