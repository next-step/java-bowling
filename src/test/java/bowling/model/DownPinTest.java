package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class DownPinTest {

    @DisplayName("핀의 동등성 체크에 성공한다")
    @Test
    void getPins_success() {
        assertThat(DownPin.valueOf(DownPin.MAX).equals(DownPin.valueOf(DownPin.MAX))).isTrue();
        assertThat(DownPin.valueOf(DownPin.MAX)).isEqualTo(DownPin.valueOf(DownPin.MAX));
    }

    @DisplayName(DownPin.MIN + "~" + DownPin.MAX + "사이의 핀을 가져오는데 성공한다")
    @ParameterizedTest
    @ValueSource(ints = {DownPin.MIN, DownPin.MAX})
    void getPins_success(int countOfDownPins) {
        // when
        DownPin downPin = DownPin.valueOf(countOfDownPins);

        // then
        assertThat(downPin).isNotNull();
    }

    @DisplayName(DownPin.MIN + "~" + DownPin.MAX + "범위를 벗어 날 시 실패한다")
    @ParameterizedTest
    @ValueSource(ints = {DownPin.MIN - 1, DownPin.MAX + 1})
    void getPins_exception(int countOfDownPins) {
        // exception
        assertThatIllegalArgumentException()
                .isThrownBy(() -> DownPin.valueOf(countOfDownPins));
    }
}