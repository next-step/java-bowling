package bowling.domain.state;

import bowling.domain.state.PinCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.Constants.WRONG_FELLED_PIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PinCountTest {
    public static final PinCount pinCount0 = new PinCount(0);
    public static final PinCount pinCount1 = new PinCount(1);
    public static final PinCount pinCount5 = new PinCount(5);
    public static final PinCount pinCount10 = new PinCount(10);

    @Test
    void create() {
        assertThat(PinCount.create(5)).isInstanceOf(PinCount.class);
    }

    @Test
    @DisplayName("첫 투구에 대한 Pin 수는 0이상 10이하여야 한다.")
    void assertFelledPin() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new PinCount(11);
        }).withMessage(WRONG_FELLED_PIN);
    }

    @Test
    @DisplayName("두 투구에 대한 Pin 수의 합계는 10이하여야 한다.")
    void assertSumFelledPin() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            pinCount5.assertSumFelledPin(pinCount10);
        }).withMessage(WRONG_FELLED_PIN);
    }

    @Test
    @DisplayName("가능한 최대 Pin의 개수를 가지고 있는지 검증한다.")
    void isMaxPinCount() {
        assertThat(pinCount10.isMaxPinCount()).isTrue();
    }

    @Test
    @DisplayName("입력받은 pinCount의 합이 최대 Pin의 개수를 갖는지 (Spare) 검증한다.")
    void isMaxPinCountBySum() {
        assertThat(pinCount5.isMaxPinCountBySum(pinCount5)).isTrue();
    }

    @Test
    @DisplayName("가능한 최소 Pin의 개수를 가지고 있는지 검증한다.")
    void isMinPinCount() {
        assertThat(pinCount0.isMinPinCount()).isTrue();
    }
}
