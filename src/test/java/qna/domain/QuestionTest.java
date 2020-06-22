package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.TestObjectFactory.*;

public class QuestionTest {

    @Test
    void delete_성공() throws CannotDeleteException {
        User user = createUser1();
        Question question = createQuestion1(user);
        LocalDateTime now = LocalDateTime.now();

        List<DeleteHistory> deleteHistory = question.delete(user, now);

        DeleteHistory expected = new DeleteHistory(ContentType.QUESTION, question.getId(), user, now);
        assertThat(deleteHistory.get(0)).isEqualTo(expected);
    }

    @Test
    void delete_답변삭제_성공() throws CannotDeleteException {
        User user = createUser1();
        Question question = createQuestion1(user);
        Answer answer = createAnswer1(user, question);
        question.addAnswer(answer);

        question.delete(user, LocalDateTime.now());

        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    void delete_다른_사람이_쓴_질문() {
        User user1 = createUser1();
        User user2 = createUser2();
        Question question = createQuestion1(user1);

        assertThatThrownBy(() -> question.delete(user2, LocalDateTime.now()))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_다른_사람이_쓴_답변_포함() {
        User user1 = createUser1();
        User user2 = createUser2();
        Question question = createQuestion1(user1);
        Answer answer = createAnswer1(user2, question);
        question.addAnswer(answer);

        assertThatThrownBy(() -> question.delete(user1, LocalDateTime.now()))
                .isInstanceOf(CannotDeleteException.class);
    }
}
