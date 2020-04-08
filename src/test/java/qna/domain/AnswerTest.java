package qna.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(1l, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");

    @Test
    void delete() {
        A1.delete();
    }

    @Test
    void toDeleteHistory() {
        DeleteHistory deleteHistory = A1.toDeleteHistory();

        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, 1l, JAVAJIGI, LocalDateTime.now()));
    }
}
