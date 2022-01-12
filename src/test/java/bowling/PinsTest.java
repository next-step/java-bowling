package bowling;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinsTest {

    @Test
    void 쓰러트린_핀_확인() {
        Pins pins = new Pins(6);
        assertThat(pins.getFallenPins()).isEqualTo(6);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void 예외_쓰러진_핀_개수(int fallenPin) {
        assertThatThrownBy(() -> new Pins(fallenPin))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("쓰러트릴 수 있는 핀의 개수는 %d개 이상 %d개 이하 입니다.", Pins.MIN_PINS_COUNT, Pins.MAX_PINS_COUNT));
    }
}