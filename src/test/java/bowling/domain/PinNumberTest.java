package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PinNumberTest {
    public static final PinNumber TEN_PIN_NUMBER = new PinNumber(10);
    public static final PinNumber ZERO_PIN_NUMBER = new PinNumber(0);
    public static final PinNumber FIVE_PIN_NUMBER = new PinNumber(5);

    @DisplayName("Pin number must be between 0 and 10")
    @Test
    void testPinNumber() {
        assertThatThrownBy(() -> new PinNumber(-1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new PinNumber(11)).isInstanceOf(IllegalArgumentException.class);
    }
}
