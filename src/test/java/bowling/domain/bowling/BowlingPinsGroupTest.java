package bowling.domain.bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class BowlingPinsGroupTest {

    @DisplayName("매 프레임의 첫 번째 투구 등 처음 칠 때 볼링 핀을 초기화 시켜 반환함")
    @Test
    public void initiateBowlingPinsGroup_초기화() {
        assertThatCode(() -> {
            BowlingPinsGroup.initiate();
        }).doesNotThrowAnyException();
    }
}
