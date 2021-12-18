package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoriesTest {

    @Test
    @DisplayName("DeleteHistories 객체 생성")
    void create() {
        DeleteHistories dh = new DeleteHistories();
        assertThat(dh).isEqualTo(new DeleteHistories());
    }

}
