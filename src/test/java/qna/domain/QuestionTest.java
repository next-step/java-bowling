package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.TestObjectFactory.*;

public class QuestionTest {

    @Test
    void delete_성공() throws CannotDeleteException {
        User user = createUser1();
        Question question = createQuestion1(user);

        question.delete(user);

        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    void delete_답변삭제_성공() throws CannotDeleteException {
        User user = createUser1();
        Question question = createQuestion1(user);
        Answer answer = createAnswer1(user, question);
        question.addAnswer(answer);

        question.delete(user);

        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    void delete_다른_사람이_쓴_질문() {
        User user1 = createUser1();
        User user2 = createUser2();
        Question question = createQuestion1(user1);

        assertThatThrownBy(() -> question.delete(user2))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_다른_사람이_쓴_답변_포함() {
        User user1 = createUser1();
        User user2 = createUser2();
        Question question = createQuestion1(user1);
        Answer answer = createAnswer1(user2, question);
        question.addAnswer(answer);

        assertThatThrownBy(() -> question.delete(user1))
                .isInstanceOf(CannotDeleteException.class);
    }
}
