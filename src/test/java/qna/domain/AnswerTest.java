package qna.domain;

import org.junit.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void 본인이_쓴_답변_삭제_성공() {
        A1.setDeleted();
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    public void 타인이_쓴_답변_삭제_실패() {
        A2.setDeleted();
        assertThat(A2.isDeleted()).isFalse();
    }
}
