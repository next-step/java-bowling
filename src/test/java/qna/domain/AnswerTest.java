package qna.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void delete() {
        assertThatCode(() -> A1.delete(UserTest.JAVAJIGI)).doesNotThrowAnyException();
    }

    @Test
    void delete_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
