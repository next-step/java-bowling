package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void testDelete() {
        DeleteHistory deleteHistory = A1.deleteBy(UserTest.JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
        assertThat(deleteHistory.isDeletedBy(A1.getWriter())).isTrue();
    }
}
