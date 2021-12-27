package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {

    @Test
    void delete() throws CannotDeleteException {
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        DeleteHistory deleteHistory = answer.delete(UserTest.JAVAJIGI);
        assertThat(answer.isDeleted())
            .isTrue();

        assertThat(deleteHistory)
            .isEqualTo(new DeleteHistory(ContentType.ANSWER, answer.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @Test
    void delete_noPermission() {
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        assertThatThrownBy(() -> answer.delete(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }
}
