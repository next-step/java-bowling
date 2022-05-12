package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 다른_사람의_답변_삭제() {
        assertThatThrownBy(() -> {
            A1.delete(A2.getWriter());
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 본인의_답변_삭제() {
        A1.delete(A1.getWriter());
    }
}
