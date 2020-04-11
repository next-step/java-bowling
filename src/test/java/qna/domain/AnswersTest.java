package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {
    private Answers answers;
    @BeforeEach
    void setUp() {
        answers = new Answers();
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);
    }

    @DisplayName("모든 댓글 삭제")
    @Test
    void delete() {
        answers.deleteAll();
        for(Answer answer: answers) {
            assertThat(answer.isDeleted()).isTrue();
        }
    }

    @DisplayName("주어진 유저가 아닌 다른 유저의 댓글이 있는지 확인")
    @Test
    void hasOtherAnswers() {
        assertThat(answers.hasOtherAnswers(UserTest.JAVAJIGI)).isTrue();
    }
}
