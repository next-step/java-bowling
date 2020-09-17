package qna.domain;

import org.junit.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void 본인이_쓴_답변_삭제_성공() throws Exception {
        A1.setDeleted(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();

        A2.setDeleted(UserTest.SANJIGI);
        assertThat(A2.isDeleted()).isTrue();
    }

    @Test(expected = CannotDeleteException.class)
    public void 타인이_쓴_답변_삭제_실패() throws Exception {
        A1.setDeleted(UserTest.SANJIGI);
        assertThat(A1.isDeleted()).isFalse();

        A2.setDeleted(UserTest.JAVAJIGI);
        assertThat(A2.isDeleted()).isFalse();
    }
}
