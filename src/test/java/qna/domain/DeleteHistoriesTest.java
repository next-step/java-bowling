package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DeleteHistoriesTest {

    @Test
    void getDeleteHistories() {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);

        DeleteHistories deleteHistories = new DeleteHistories(QuestionTest.Q1, answers);
        assertThat(deleteHistories.getDeleteHistories()).hasSize(3);
    }
}