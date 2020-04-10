package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 =
            new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 =
            new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("삭제")
    @Test
    void delete() {
        DeleteHistory deleteHistory = A1.delete();
        assertThat(A1.isDeleted()).isEqualTo(true);
        assertThat(deleteHistory).isEqualTo(
                new DeleteHistory(ContentType.ANSWER, A1.getId(),
                        A1.getWriter(), LocalDateTime.now()));
    }
}
