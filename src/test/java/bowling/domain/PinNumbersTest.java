package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static bowling.domain.PinNumberTest.*;

public class PinNumbersTest {

    @DisplayName("PinNumbers must be strike when it is composed of one PinNumber")
    @Test
    void testStrike() {
        PinNumbers pinNumbers = new PinNumbers(Arrays.asList(TEN_PIN_NUMBER));
        assertThat(pinNumbers.isStrike()).isTrue();
    }

    @DisplayName("PinNumbers must be strike when it is composed of one PinNumber")
    @Test
    void testSpare() {
        PinNumbers pinNumbers = new PinNumbers(Arrays.asList(FIVE_PIN_NUMBER, FIVE_PIN_NUMBER));
        assertThat(pinNumbers.isSpare()).isTrue();
    }
}
