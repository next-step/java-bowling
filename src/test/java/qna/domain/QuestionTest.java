package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswerTest.A1;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void deleteBy_pass() throws CannotDeleteException {
        // given
        User user = UserTest.JAVAJIGI;
        Q1.addAnswer(A1);
        LocalDateTime now = LocalDateTime.now();

        // when
        List<DeleteHistory> deleteHistories = Q1.deleteBy(user);

        // then
        assertThat(deleteHistories).isEqualTo(
                Arrays.asList(
                        new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), now),
                        new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), now)
                )
        );
    }

    @Test
    void deleteBy_fail() {
        // given
        User user = UserTest.SANJIGI;
        Q1.addAnswer(A1);

        // when & then
        assertThatThrownBy(() -> {
            Q1.deleteBy(user);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
