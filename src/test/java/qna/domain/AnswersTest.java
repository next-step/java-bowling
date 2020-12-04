package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {
    @Test
    @DisplayName("특정 유저와 답변글의 모든 답변자 같은 경우 hasOtherOwnerDifferentFrom() false")
    public void questionerAndAnswererAllSameTest() {
        User loginUser = new User(1L, "testUser", "1234", "userName", "user@naver.com");
        Question question = new Question(1L, "title1", "contents1");
        Answer answer1 = new Answer(loginUser, question, "answer1");
        Answer answer2 = new Answer(loginUser, question, "answer2");
        Answer answer3 = new Answer(loginUser, question, "answer3");
        Answers answers = new Answers(answer1, answer2, answer3);

        assertThat(answers.hasOtherOwnerDifferentFrom(loginUser)).isFalse();
    }

    @Test
    @DisplayName("특정 유저와 답변글의 답변자가 다른 경우 hasOtherOwnerDifferentFrom() true")
    public void questionerAndAnswererDifferentTest() {
        User loginUser = new User(1L, "testUser", "1234", "userName", "user@naver.com");
        User otherUser = new User(2L, "testUser2", "1234", "userName2", "user2@naver.com");
        Question question = new Question(1L, "title1", "contents1");
        Answer answer1 = new Answer(loginUser, question, "answer1");
        Answer answer2 = new Answer(otherUser, question, "answer2");
        Answers answers = new Answers(answer1, answer2);

        assertThat(answers.hasOtherOwnerDifferentFrom(loginUser)).isTrue();
    }
}
