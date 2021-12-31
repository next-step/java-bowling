package qna.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    @Test
    public void delete() throws CannotDeleteException {
        Question question = new Question(3L, "test title", "test content");
        User user = new User(1L,"mj","1234","minjoon","minjoon@naver.com");

        Answers answers = new Answers();
        Answer answerA = new Answer(1L,user,question,"answer testA");
        Answer answerB = new Answer(2L,user,question,"answer testB");

        answers.add(answerA);
        answers.add(answerB);
        answers.delete(user);

        assertThat(answers.isDeleted()).isTrue();
    }

    @Test
    public void checkDeleteValidation() {
        Question question = new Question(3L, "test title", "test content");
        User userA = new User(1L,"A","1234","minjoon","minjoon@naver.com");
        User userB = new User(2L,"B","1234","minjoon","minjoon@naver.com");

        Answers answers = new Answers();
        Answer answerA = new Answer(1L,userA,question,"answer testA");
        Answer answerB = new Answer(2L,userB,question,"answer testB");

        answers.add(answerA);
        answers.add(answerB);

        assertThatThrownBy(() -> {
            answers.delete(userA);
        }).isInstanceOf(CannotDeleteException.class).hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
