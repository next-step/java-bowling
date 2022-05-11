package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class AnswersTest {

    @Test
    void create() {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);

        assertThat(answers.getAnswers()).hasSize(2);
    }

}