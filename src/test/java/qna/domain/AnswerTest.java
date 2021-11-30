package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q2, "Answers Contents3");
    public static final Answer A4 = new Answer(UserTest.SANJIGI, QuestionTest.Q2, "Answers Contents4");

    public static final DeleteHistory DELETE_HISTORY1 = new DeleteHistory(ContentType.ANSWER, null, UserTest.JAVAJIGI, LocalDateTime.now());
    public static final DeleteHistory DELETE_HISTORY2 = new DeleteHistory(ContentType.ANSWER, null, UserTest.SANJIGI, LocalDateTime.now());
    public static final DeleteHistory DELETE_HISTORY3 = new DeleteHistory(ContentType.ANSWER, null, UserTest.JAVAJIGI, LocalDateTime.now());
    public static final DeleteHistory DELETE_HISTORY4 = new DeleteHistory(ContentType.ANSWER, null, UserTest.SANJIGI, LocalDateTime.now());

    @Test
    void testDelete() throws CannotDeleteException {
        assertThat(A1.delete(UserTest.JAVAJIGI)).isEqualTo(DELETE_HISTORY1);
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> A2.delete(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
        assertThat(A2.delete(UserTest.SANJIGI)).isEqualTo(DELETE_HISTORY2);
    }
}
