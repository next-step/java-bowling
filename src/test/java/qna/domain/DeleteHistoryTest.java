package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DeleteHistoryTest {

    @DisplayName("DeleteHistory 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        DeleteHistory deleteHistory = new DeleteHistory(AnswerTest.A1);

        assertThat(deleteHistory).isNotNull();
    }
}