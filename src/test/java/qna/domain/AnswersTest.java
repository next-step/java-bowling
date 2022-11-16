package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    private Answers answers;

    @BeforeEach
    void setUp() {
        answers = new Answers();
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);
    }

    @Test
    void add() {
        assertThat(answers.answers()).containsExactly(AnswerTest.A1, AnswerTest.A2);
    }

    @Test
    void answers_전쳬_삭제() {
        DeleteHistories deleteHistories = new DeleteHistories();
        answers.deleteAll(deleteHistories);

        assertThat(AnswerTest.A1.isDeleted()).isTrue();
        assertThat(AnswerTest.A2.isDeleted()).isTrue();
        assertThat(deleteHistories.histories()).hasSize(2);
    }

}