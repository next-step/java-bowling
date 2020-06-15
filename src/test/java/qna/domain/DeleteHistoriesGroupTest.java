package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class DeleteHistoriesGroupTest {

    @DisplayName("객체 정상 생성")
    @Test
    public void makeDeleteHistoriesGroup_정상() {
        assertThatCode(() -> {
            new DeleteHistoriesGroup();
        }).doesNotThrowAnyException();
    }
}
