package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoriesTest {

    @Test
    void addQuestion() {
        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.addDeleteHistoryByQuestion(QuestionTest.Q1);

        assertThat(deleteHistories.getDeleteHistories().size()).isEqualTo(1);
    }

    @Test
    void addAnswer() {
        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.addDeleteHistoryByAnswer(AnswerTest.A1);
        deleteHistories.addDeleteHistoryByAnswer(AnswerTest.A3);

        assertThat(deleteHistories.getDeleteHistories().size()).isEqualTo(2);
    }
}
