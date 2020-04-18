package seul.bowling.domain;

import org.junit.jupiter.api.Test;
import seul.bowling.exception.BowlingException;
import seul.bowling.exception.ExceptionType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinTest {
    @Test
    void pin() {
        int clearPin = 10;

        Pin pin = new Pin(clearPin);

        assertThat(pin.getPin()).isEqualTo(clearPin);
    }

    @Test
    void pin_invalidClearPinCount() {
        int clearPin = 11;

        assertThatThrownBy(() -> new Pin(clearPin))
                .isInstanceOf(BowlingException.class)
                .hasMessageContaining(ExceptionType.INVALID_CLEAR_PIN_COUNT.getErrorMessage());
    }
}
