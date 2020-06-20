package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void delete_성공() throws CannotDeleteException {
        LocalDateTime now = LocalDateTime.now();
        DeleteHistory expected = new DeleteHistory(ContentType.ANSWER, A1.getId(), UserTest.JAVAJIGI, now);

        DeleteHistory deleteHistory = A1.delete(UserTest.JAVAJIGI, now);

        assertThat(deleteHistory).isEqualTo(expected);
    }

    @Test
    void delete_다른_사람이_쓴_답변() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI, LocalDateTime.now()))
                .isInstanceOf(CannotDeleteException.class);
    }
}
