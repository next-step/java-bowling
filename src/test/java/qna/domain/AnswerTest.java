package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public Answer a;

    @BeforeEach
    void setUp() {
        a = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
    }

    @Test
    void 삭제_성공() throws CannotDeleteException {
        assertThat(a.isDeleted()).isFalse();
        DeleteHistory expected = new DeleteHistory(ContentType.ANSWER, a.getId(), a.getWriter(), LocalDateTime.now());

        DeleteHistory history = a.delete(a.getWriter());

        assertThat(a.isDeleted()).isTrue();
        assertThat(history).isEqualTo(expected);
    }

    @Test
    void 삭제_실패() {
        assertThatThrownBy(() -> a.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
