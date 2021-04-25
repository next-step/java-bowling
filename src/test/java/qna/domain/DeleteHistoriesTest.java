package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DeleteHistoriesTest {

    @DisplayName("DeleteHistories 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        DeleteHistories deleteHistories = new DeleteHistories();
        // then
        assertThat(deleteHistories).isNotNull();
    }

    @DisplayName("DeleteHistories 인스턴스 리스트로 생성 여부 테스트")
    @Test
    void 생성_리스트() {
        // given
        List<DeleteHistory> deleteHistoryList = new ArrayList<>();

        // when
        DeleteHistories deleteHistories = new DeleteHistories(deleteHistoryList);

        // then
        assertThat(deleteHistories).isNotNull();
    }
}