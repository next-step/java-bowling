package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PinNumberTest {

    @DisplayName("Pin number must be between 0 and 10")
    @Test
    void testPinNumber() {
        assertThatThrownBy(() -> new PinNumber(-1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new PinNumber(11)).isInstanceOf(IllegalArgumentException.class);
    }
}
