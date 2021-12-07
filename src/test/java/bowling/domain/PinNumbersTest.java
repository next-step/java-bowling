package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static bowling.domain.PinNumberTest.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PinNumbersTest {
    public static final PinNumbers PIN_NUMBERS_STRIKE = new PinNumbers(Collections.singletonList(TEN_PIN_NUMBER));
    public static final PinNumbers PIN_NUMBERS_SPARE_FIRST_EIGHT = new PinNumbers(Arrays.asList(EIGHT_PIN_NUMBER, TWO_PIN_NUMBER));
    public static final PinNumbers PIN_NUMBERS_SPARE_FIRST_FIVE = new PinNumbers(Arrays.asList(FIVE_PIN_NUMBER, FIVE_PIN_NUMBER));
    public static final PinNumbers PIN_NUMBERS_SPARE_FIRST_ZERO = new PinNumbers(Arrays.asList(ZERO_PIN_NUMBER, TEN_PIN_NUMBER));
    public static final PinNumbers PIN_NUMBERS_FIVE = new PinNumbers(Arrays.asList(FIVE_PIN_NUMBER, ZERO_PIN_NUMBER));
    public static final PinNumbers PIN_NUMBERS_ZERO = new PinNumbers(Arrays.asList(ZERO_PIN_NUMBER, ZERO_PIN_NUMBER));

    @DisplayName("PinNumbers must be strike when it is composed of one PinNumber")
    @Test
    void testStrike() {
        assertThat(PIN_NUMBERS_STRIKE.isStrike()).isTrue();
    }

    @DisplayName("PinNumbers must be spare when its sum is ten")
    @Test
    void testSpare() {
        assertThat(PIN_NUMBERS_SPARE_FIRST_EIGHT.isSpare()).isTrue();
        assertThat(PIN_NUMBERS_SPARE_FIRST_FIVE.isSpare()).isTrue();
        assertThat(PIN_NUMBERS_SPARE_FIRST_ZERO.isSpare()).isTrue();
    }
}
