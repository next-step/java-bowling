package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void delete() {
        assertThatCode(() -> A1.delete(UserTest.JAVAJIGI)).doesNotThrowAnyException();
    }

    @Test
    void delete_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_히스토리_생성() throws CannotDeleteException {
        A1.delete(UserTest.JAVAJIGI);
        assertThat(A1.deleteHistory()).isEqualToIgnoringGivenFields(
            new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()), "createDate");
    }
}
