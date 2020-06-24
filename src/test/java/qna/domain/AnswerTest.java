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
    void deleteAnswerTest_성공() throws CannotDeleteException {
        DeleteHistory deleteHistory = A1.deleteAnswer(UserTest.JAVAJIGI);
        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()));
    }

    @Test
    void deleteAnswerTest_실패() throws CannotDeleteException {
        assertThatThrownBy(() -> A2.deleteAnswer(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
