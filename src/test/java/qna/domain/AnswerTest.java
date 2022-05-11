package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void deleteAnswer() throws CannotDeleteException {
        A1.deleteAnswer(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    void cantDeleteAnswer() {
        assertThatThrownBy(() -> A1.deleteAnswer(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
