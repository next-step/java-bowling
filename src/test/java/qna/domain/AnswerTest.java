package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void 삭제_성공() throws CannotDeleteException
    {
        User user = UserTest.JAVAJIGI;

        A1.delete(user);

        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    public void 삭제_실패()
    {
        User user = UserTest.SANJIGI;

        assertThatThrownBy(() ->
            A1.delete(user)).isInstanceOf(CannotDeleteException.class);
    }
}
