package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {
    private Answers answers;

    @BeforeEach
    void setUp() {
        answers = new Answers();
    }

    @Test
    void add() {
        answers.add(AnswerTest.A1);

        assertThat(answers.size()).isEqualTo(1);
    }
}
