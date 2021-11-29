package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void testDelete() throws CannotDeleteException {
        assertThat(A1.delete(UserTest.JAVAJIGI)).isTrue();
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> A2.delete(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
        assertThat(A2.delete(UserTest.SANJIGI)).isTrue();
    }
}
