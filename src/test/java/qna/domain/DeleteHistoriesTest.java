package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoriesTest {
    @DisplayName("정상적으로 deleteHistories에 이력 남김 확인")
    @Test
    void insert() {
        DeleteHistories deleteHistories = new DeleteHistories();
        List<DeleteHistory> deleteHistoryList = deleteHistories.getDeleteHistories();

        assertThat(deleteHistoryList).isEmpty();
        deleteHistories.insert(QuestionTest.Q1, AnswersTest.AS1);
        assertThat(deleteHistoryList).contains(new DeleteHistory(QuestionTest.Q1));
        assertThat(deleteHistoryList).contains(new DeleteHistory(AnswerTest.A1));
    }
}