package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    private static final Answers answers = new Answers();

    @Test
    void add() {
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);
        assertThat(answers.answers()).containsExactly(AnswerTest.A1, AnswerTest.A2);
    }

}