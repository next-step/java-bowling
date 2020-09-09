package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoriesTest {
    public static final DeleteHistory D1 = new DeleteHistory(ContentType.ANSWER, 0L, UserTest.JAVAJIGI, LocalDateTime.now());
    public static final DeleteHistory D2 = new DeleteHistory(ContentType.ANSWER, 1L, UserTest.JAVAJIGI, LocalDateTime.now());
    public static final DeleteHistory D3 = new DeleteHistory(ContentType.QUESTION, 2L, UserTest.SANJIGI, LocalDateTime.now());

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

    @DisplayName("히스토리 목록에 다른 히스토리 추가 테스트")
    @Test
    void addAll() {
        // given
        DeleteHistories deleteHistories = DeleteHistories.of(D1);

        // when
        List<DeleteHistory> deleteHistoryList = deleteHistories.addAll(Arrays.asList(D2, D3)).getDeleteHistoryList();

        // then
        assertThat(deleteHistoryList).containsExactly(D1, D2, D3);
    }
}
