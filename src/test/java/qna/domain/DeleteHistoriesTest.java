package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoriesTest {

    @Test
    @DisplayName("기본생성자로 생성된 DeleteHistories는 서로 같은 상태다.")
    void create() {
        assertThat(new DeleteHistories()).isEqualTo(new DeleteHistories());
    }
}
