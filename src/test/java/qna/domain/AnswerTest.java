package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.*;
import static qna.domain.QuestionTest.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, Q1, "Answers Contents2");

    @Test
    void delete() throws CannotDeleteException {
        A1.delete(UserTest.JAVAJIGI, new DeleteHistories());
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    void delete_error() {
        assertThatThrownBy(
                () -> A2.delete(UserTest.JAVAJIGI, new DeleteHistories())
        ).isInstanceOf(CannotDeleteException.class);

    }
}
