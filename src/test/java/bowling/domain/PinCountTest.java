package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.Constants.WRONG_FELLED_PIN;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PinCountTest {

    @Test
    @DisplayName("첫 투구에 대한 Pin 수는 0이상 10이하여야 한다.")
    void assertFelledPin() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            PinCount pinCount = new PinCount(11);
        }).withMessage(WRONG_FELLED_PIN);
    }

    @Test
    @DisplayName("두 투구에 대한 Pin 수의 합계는 10이하여야 한다.")
    void assertSumFelledPin() {
        PinCount pinCount = new PinCount(5);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            pinCount.assertSumFelledPin(6);
        }).withMessage(WRONG_FELLED_PIN);
    }
}
