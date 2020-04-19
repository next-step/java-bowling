package seul.bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import seul.bowling.exception.BowlingException;
import seul.bowling.exception.ExceptionType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinsTest {
    @Test
    void addPin() {
        Pins pins = new Pins();

        pins.addPin(10, false);

        assertThat(pins.getPins()).hasSize(1);
    }

    @Test
    void addPin_invalidClearPinCount() {
        Pins pins = new Pins();

        pins.addPin(2, false);

        assertThatThrownBy(() -> pins.addPin(9, false))
                .isInstanceOf(BowlingException.class)
                .hasMessageContaining(ExceptionType.INVALID_CLEAR_PIN_COUNT.getErrorMessage());
    }

    @ParameterizedTest
    @CsvSource(value = {"10:TRUE", "9:FALSE"}, delimiter = ':')
    void allClear(int clearPin, boolean expected) {
        Pins pins = new Pins();
        pins.addPin(clearPin, false);

        boolean allClear = pins.allClear();

        assertThat(allClear).isEqualTo(expected);
    }

    @Test
    void endDefaultPlayCount() {
        Pins pins = new Pins();
        pins.addPin(1, false);
        pins.addPin(9, false);
        pins.addPin(1, false);

        assertThat(pins.endDefaultPlayCount(false)).isEqualTo(true);
    }
}
