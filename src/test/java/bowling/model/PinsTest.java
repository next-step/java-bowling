package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PinsTest {

    @DisplayName("핀의 동등성 체크에 성공한다")
    @Test
    void getPins_success() {
        assertThat(Pins.valueOf(Pins.MAX).equals(Pins.valueOf(Pins.MAX))).isTrue();
        assertThat(Pins.valueOf(Pins.MAX)).isEqualTo(Pins.valueOf(Pins.MAX));
    }

    @DisplayName(Pins.MIN + "~" + Pins.MAX + "사이의 핀을 가져오는데 성공한다")
    @ParameterizedTest
    @ValueSource(ints = {Pins.MIN, Pins.MAX})
    void getPins_success(int countOfDownPins) {
        // when
        Pins pins = Pins.valueOf(countOfDownPins);

        // then
        assertThat(pins).isNotNull();
    }

    @DisplayName(Pins.MIN + "~" + Pins.MAX + "범위를 벗어 날 시 실패한다")
    @ParameterizedTest
    @ValueSource(ints = {Pins.MIN - 1, Pins.MAX + 1})
    void getPins_exception(int countOfDownPins) {
        // exception
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Pins.valueOf(countOfDownPins));
    }
}