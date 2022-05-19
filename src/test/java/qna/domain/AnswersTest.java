package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {
    @Test
    void 삭제에대한답변조건() {
        Answers answers = new Answers();
        answers.addAnswer(AnswerTest.A1);
        assertThat(answers.canDeletedAnswerCondition(UserTest.JAVAJIGI)).isFalse();
    }
    @Test
    void 답변이없을경우(){
        Answers answers = new Answers();
        assertThat(answers.canDeletedAnswerCondition(UserTest.JAVAJIGI)).isFalse();
    }
}
