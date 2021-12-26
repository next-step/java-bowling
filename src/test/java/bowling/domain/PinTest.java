package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class PinTest {
    @DisplayName("Pin 생성")
    @Test
    void create() {
        int expected = 10;
        Pin pin = Pin.of(expected);
        assertThat(pin.getFallenPinCount()).isEqualTo(expected);
        assertThat(pin).isEqualTo(new Pin(expected));
    }

    @DisplayName("Pin 입력 가능 범위가 아닐 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {Pin.MIN_PIN_COUNT - 1, Pin.MAX_PIN_COUNT + 1})
    void exception(int invalidPins) {
        assertThatThrownBy(() -> Pin.of(invalidPins))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Pin 상태 검증")
    @Test
    void state() {
        assertAll(
                () -> assertThat(Pin.of(0).isGutter()).isTrue(),
                () -> assertThat(Pin.of(10).isStrike()).isTrue(),
                () -> assertThat(Pin.first().isSpare(Pin.of(10))).isTrue()
        );
    }
}
