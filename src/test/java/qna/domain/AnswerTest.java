package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void delete() {
        assertThat(A1.delete())
            .isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()));
    }
}
