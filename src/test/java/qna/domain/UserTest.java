package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswerTest.A1;
import static qna.domain.QuestionTest.Q1;

public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @Test
    void deleteQuestion_pass() throws CannotDeleteException {
        // given
        User user = UserTest.JAVAJIGI;
        Q1.addAnswer(A1);
        LocalDateTime now = LocalDateTime.now();

        // when
        List<DeleteHistory> deleteHistories = user.deleteQuestion(Q1);

        // then
        assertThat(deleteHistories).isEqualTo(
                Arrays.asList(
                        new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), now),
                        new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), now)
                )
        );
    }

    @Test
    void deleteQuestion_fail() {
        // given
        User user = UserTest.SANJIGI;
        Q1.addAnswer(A1);

        // when & then
        assertThatThrownBy(() -> {
            user.deleteQuestion(Q1);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
