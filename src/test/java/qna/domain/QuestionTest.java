package qna.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("질문자와 로그인한 사람이 일치하지 않으면 예외 반환")
    void isOwnerException(){

        User user1 = new User();
        assertThatThrownBy(() -> Q1.isOwner(user1))
            .isInstanceOf(CannotDeleteException.class);

        User user2 = new User();
        assertThatThrownBy(() -> Q2.isOwner(user2))
            .isInstanceOf(CannotDeleteException.class);

    }

    @Test
    @DisplayName("질문자와 답변자가 일치하지 않으면 삭제 불가 반환")
    void isAnswerOwner() {

        User user1 = new User("user1", "psswd", "user1", "user1@gmail.com");
        User user2 = new User("user2", "psswd", "user2", "user2@gmail.com");

        Answer answer1 = new Answer(user2,Q1,"test1");
        Q1.addAnswer(answer1);
        assertThatThrownBy(() -> Q1.isAnswerOwner(user1))
            .isInstanceOf(CannotDeleteException.class);

    }
}
