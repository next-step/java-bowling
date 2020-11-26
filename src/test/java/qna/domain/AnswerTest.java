package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void deleteAnswer() {
        DeleteHistory deleteHistory = A1.delete(UserTest.JAVAJIGI);

        assertThat(deleteHistory).isEqualTo(DeleteHistory.newAnswer(A1.getId(), A1.getWriter()));
        assertThat(A1.isDeleted()).isEqualTo(true);
    }

    @Test
    public void deleteAnswerByOtherUser() {
        assertThatThrownBy(() -> {
            A1.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
