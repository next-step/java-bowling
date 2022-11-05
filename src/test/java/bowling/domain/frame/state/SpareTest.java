package bowling.domain.frame.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpareTest {
    @Test
    @DisplayName("조건을 만족하지 않는 핀의 갯수를 넣어줄 때 예외를 던진다.")
    void createWithInvalidPins() {
        assertThatThrownBy(() -> new Spare(new Pins(4), new Pins(5)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("스페어의 조건을 만족하지 않습니다");
    }

    @Test
    @DisplayName("스페어 상태에서는 볼링을 시도할 시 예외를 던진다.")
    void bowl() {
        assertThatThrownBy(() -> new Spare(new Pins(4), new Pins(6)).bowl(10))
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("스페어 상태에서는 해당 프레임의 종료를 알린다.")
    void isFinish() {
        assertThat(new Spare(new Pins(4), new Pins(6)).isFinish()).isTrue();
    }
}
