package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DeleteHistoriesTest {

    @Test
    @DisplayName("DeleteHistories 추가 테스트")
    void deleteHistoriesTest_add() {
        Long questionId = 1L;

        User writer = new User();
        DeleteHistories deleteHistories = new DeleteHistories();

        DeleteHistory deleteHistory = new DeleteHistory(ContentType.QUESTION, questionId, writer, LocalDateTime.now());
        deleteHistories.add(deleteHistory);

        List<DeleteHistory> newDeleteHistories = deleteHistories.getDeleteHistories();
        assertThat(newDeleteHistories.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("DeleteHistories get 테스트")
    void deleteHistories_getDeleteHistories() {
        Long questionId = 1L;

        User writer = new User();
        DeleteHistories deleteHistories = new DeleteHistories();

        DeleteHistory deleteHistory = new DeleteHistory(ContentType.QUESTION, questionId, writer, LocalDateTime.now());
        deleteHistories.add(deleteHistory);

        List<DeleteHistory> newDeleteHistories = deleteHistories.getDeleteHistories();
        assertThat(newDeleteHistories.get(0)).isEqualTo(new DeleteHistory(ContentType.QUESTION, questionId, writer, LocalDateTime.now()));
    }
}