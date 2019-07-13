package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PinsTest {
    @Test
    void 쓰러진_핀의_개수를_생성한다() {
        int fallenPins = 7;
        Pins pins = Pins.from(fallenPins);

        assertThat(pins.isMatch(pins)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void 쓰러진_핀의_개수가_0개와_10개_사이를_벗어날_경우_예외가_발생한다(int fallenPins) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    Pins.from(fallenPins);
                }).withMessage(Pins.ALERT_OUT_OF_PINS_RANGE);
    }
}
