package seul.bowling.domain.pin;

import org.junit.jupiter.api.Test;
import seul.bowling.exception.BowlingException;
import seul.bowling.exception.ExceptionType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinTest {
    @Test
    void of() {
        int clearPin = 10;

        Pin pin = Pin.of(clearPin);

        assertThat(pin.getPin()).isEqualTo(clearPin);
    }

    @Test
    void of_invalidClearPinCount() {
        int clearPin = 11;

        assertThatThrownBy(() -> Pin.of(clearPin))
                .isInstanceOf(BowlingException.class)
                .hasMessageContaining(ExceptionType.INVALID_CLEAR_PIN_COUNT.getErrorMessage());
    }
}
