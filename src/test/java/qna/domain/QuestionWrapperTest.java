package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QuestionWrapperTest {

    @Test
    void shouldValidateQuestionOwner() {
        User userA = new User("userA", "password", "userA", "email");
        User userB = new User("userB", "password", "nameB", "email");

        Question questionOfUserA = new Question("title", "content").writeBy(userA);

        assertThatThrownBy(() -> new DeleteQuestion(questionOfUserA, userB))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void shouldValidateAnswerOwner() {
        User userA = new User("userA", "password", "userA", "email");
        User userB = new User("userB", "password", "userB", "email");
        Question questionOfUserA = new Question("title", "content").writeBy(userA);
        questionOfUserA.addAnswer(new Answer(userB, questionOfUserA, "userB가 쓴 댓글"));

        assertThatThrownBy(() -> new DeleteQuestion(questionOfUserA, userA))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void shouldGetDeleteHistory() throws CannotDeleteException {
        User user = new User("userA", "password", "userA", "email");
        Question question = new Question("title", "content").writeBy(user);
        Answer answer = new Answer(user, question, "댓글");
        question.addAnswer(answer);

        assertThat(new DeleteQuestion(question, user).deleteHistory())
                .containsExactly(
                        DeleteHistory.withQuestion(question),
                        DeleteHistory.withAnswer(answer)
                );
    }

}
