package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class AnswersTest {

    @Test
    void isOwner() {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);
        Assertions.assertThat(answers.isOwner(UserTest.JAVAJIGI)).isFalse();
    }
}