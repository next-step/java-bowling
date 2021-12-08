package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ThrowTest {
    public static final Pitch TEN_PIN_NUMBER = new Pitch(10);
    public static final Pitch ZERO_PIN_NUMBER = new Pitch(0);
    public static final Pitch TWO_PIN_NUMBER = new Pitch(2);
    public static final Pitch FIVE_PIN_NUMBER = new Pitch(5);
    public static final Pitch EIGHT_PIN_NUMBER = new Pitch(8);

    @DisplayName("Pin number must be between 0 and 10")
    @Test
    void testPinNumber() {
        assertThatThrownBy(() -> new Pitch(-1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Pitch(11)).isInstanceOf(IllegalArgumentException.class);
    }
}
