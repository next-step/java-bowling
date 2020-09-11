package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoriesTest {
    public static final DeleteHistory D1 = DeleteHistory.answer(0L, UserTest.JAVAJIGI);
    public static final DeleteHistory D2 = DeleteHistory.answer(1L, UserTest.JAVAJIGI);
    public static final DeleteHistory D3 = DeleteHistory.question(2L, UserTest.SANJIGI);

    @DisplayName("생성 테스트")
    @Test
    void create() {
        // given
        DeleteHistories deleteHistories = DeleteHistories.of(D1, D2);

        // when
        List<DeleteHistory> deleteHistoriesList = deleteHistories.getDeleteHistoryList();

        // then
        assertThat(deleteHistoriesList).hasSize(2);
    }

    @DisplayName("히스토리 목록에 다른 히스토리 병합 테스트")
    @Test
    void merge() {
        // given
        DeleteHistories deleteHistories = DeleteHistories.of(D1);

        // when
        DeleteHistories result = deleteHistories.merge(DeleteHistories.of(D2, D3));

        // then
        assertThat(result.getDeleteHistoryList()).containsExactly(D1, D2, D3);
    }
}
