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
    void deleteBy_pass() throws CannotDeleteException {
        // given
        User user = UserTest.JAVAJIGI;
        LocalDateTime now = LocalDateTime.now();

        // when
        DeleteHistory deleteHistory = A1.deleteBy(user);

        // then
        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), now));
    }

    @Test
    void deleteBy_fail() {
        // given
        User user = UserTest.SANJIGI;

        // when & then
        assertThatThrownBy(() -> {
            A1.deleteBy(user);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
