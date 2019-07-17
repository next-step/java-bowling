package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PinTest {

    @DisplayName("핀의 동등성 체크에 성공한다")
    @Test
    void getPins_success() {
        assertThat(Pin.valueOf(Pin.MAX).equals(Pin.valueOf(Pin.MAX))).isTrue();
        assertThat(Pin.valueOf(Pin.MAX)).isEqualTo(Pin.valueOf(Pin.MAX));
    }

    @DisplayName(Pin.MIN + "~" + Pin.MAX + "사이의 핀을 가져오는데 성공한다")
    @ParameterizedTest
    @ValueSource(ints = {Pin.MIN, Pin.MAX})
    void getPins_success(int countOfDownPins) {
        // when
        Pin pin = Pin.valueOf(countOfDownPins);

        // then
        assertThat(pin).isNotNull();
    }

    @DisplayName(Pin.MIN + "~" + Pin.MAX + "범위를 벗어 날 시 실패한다")
    @ParameterizedTest
    @ValueSource(ints = {Pin.MIN - 1, Pin.MAX + 1})
    void getPins_exception(int countOfDownPins) {
        // exception
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Pin.valueOf(countOfDownPins));
    }
}