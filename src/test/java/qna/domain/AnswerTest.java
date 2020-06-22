package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.TestObjectFactory.*;

public class AnswerTest {

    @Test
    void delete_성공() throws CannotDeleteException {
        User user = createUser1();
        Answer answer = createAnswer1(user, createQuestion1(user));
        LocalDateTime now = LocalDateTime.now();

        DeleteHistory deleteHistory = answer.delete(user, now);

        DeleteHistory expected = new DeleteHistory(ContentType.ANSWER, answer.getId(), user, now);
        assertThat(deleteHistory).isEqualTo(expected);
    }

    @Test
    void delete_다른_사람이_쓴_답변() {
        User user1 = createUser1();
        User user2 = createUser2();
        Answer answer = createAnswer1(user1, createQuestion1(user1));

        assertThatThrownBy(() -> answer.delete(user2, LocalDateTime.now()))
                .isInstanceOf(CannotDeleteException.class);
    }
}
