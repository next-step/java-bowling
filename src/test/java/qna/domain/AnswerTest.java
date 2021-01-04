package qna.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q2, "Answers Contents3");

    @Test
    void delete() {
        assertThat(A1.isDeleted()).isFalse();
        A1.delete();
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    void delete_shouldReturnDeleteHistory() {
        assertThat(A1.delete()).isEqualTo(DeleteHistory.of(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()));
    }
}
