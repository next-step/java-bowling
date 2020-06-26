package bowling.domain;

import bowling.domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class PinsTest {

    @DisplayName("쓰러뜨린 핀 갯수 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5})
    void rollTest(int pin) {
        // given
        Pins pins = new Pins();

        // when
        pins.roll(pin);

        // then
        assertThat(pins.getTotalPins()).isEqualTo(pin);
    }

    @DisplayName("쓰러뜨린 핀의 합계가 유효한 범위인지 테스트")
    @Test
    void rollOverTen() {
        Pins pins = new Pins();
        assertThatThrownBy(() -> {
            pins.roll(5);
            pins.roll(6);

        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("스트라이크 이후 던지기 테스트")
    @Test
    void testRollAfterStrike() {
        Pins pins = new Pins();
        assertThatThrownBy(() -> {
            pins.roll(10);
            pins.roll(3);

        }).isInstanceOf(IllegalArgumentException.class);
    }
}

