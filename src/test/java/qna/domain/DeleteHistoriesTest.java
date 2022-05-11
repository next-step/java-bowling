package qna.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class DeleteHistoriesTest {

    @Test
    void getDeleteHistories() {
        DeleteHistories deleteHistories = new DeleteHistories(QuestionTest.Q1, new Answers(List.of(AnswerTest.A1, AnswerTest.A2)));
        assertThat(deleteHistories.getDeleteHistories()).hasSize(3);
    }
}