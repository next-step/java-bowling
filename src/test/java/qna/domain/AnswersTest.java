package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    @Test
    void add() {
        Answers answers = new Answers();

        answers.add(AnswerTest.A1);

        assertThat(answers.size()).isEqualTo(1);
    }
}
