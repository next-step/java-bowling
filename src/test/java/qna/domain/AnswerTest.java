package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void deleteByUser() throws CannotDeleteException {
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        DeleteHistory deleteHistory = answer.deleteByUser(UserTest.JAVAJIGI);
        assertThat(deleteHistory)
                .isEqualTo(new DeleteHistory(ContentType.ANSWER, answer.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));
        assertThatFullyDeleted(answer);
    }

    @Test
    public void deleteByUser_ShouldThrow_WhenNull() {
        assertThatNullPointerException().isThrownBy(() -> A1.deleteByUser(null));
    }

    @Test
    public void deleteByUser_ShouldThrow_WhenNotOwner() {
        assertThatThrownBy(() -> A1.deleteByUser(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> A2.deleteByUser(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    private void assertThatFullyDeleted(Answer answer) {
        assertThat(answer.isDeleted()).isTrue();
    }
}
