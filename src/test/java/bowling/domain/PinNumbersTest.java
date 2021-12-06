package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static bowling.domain.PinNumberTest.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PinNumbersTest {

    @DisplayName("PinNumbers must be strike when it is composed of one PinNumber")
    @Test
    void testStrike() {
        PinNumbers pinNumbers = new PinNumbers(Collections.singletonList(TEN_PIN_NUMBER));
        assertThat(pinNumbers.isStrike()).isTrue();
    }

    @DisplayName("PinNumbers must be spare when its sum is ten")
    @Test
    void testSpare() {
        PinNumbers pinNumbers = new PinNumbers(Arrays.asList(FIVE_PIN_NUMBER, FIVE_PIN_NUMBER));
        assertThat(pinNumbers.isSpare()).isTrue();
    }
}
