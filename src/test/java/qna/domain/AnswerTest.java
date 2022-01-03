package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import static org.assertj.core.api.Assertions.assertThat;
public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void delete_성공() throws CannotDeleteException {
        assertThat(A1.isDeleted()).isFalse();
        A1.delete(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    public void delete_다른_사람이_쓴_글() throws CannotDeleteException {
        assertThat(A1.isDeleted()).isFalse();
        A1.delete(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }
}
