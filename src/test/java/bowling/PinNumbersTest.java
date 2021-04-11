package bowling;

import bowling.domain.FrameState;
import bowling.domain.PinNumber;
import bowling.domain.PinNumbers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PinNumbersTest {

    @Test
    @DisplayName("1 ~ 9 프레임에서 첫 번째 투구와 투 번째 투구의 합이 10을 초과하면 예외처리가 발생한다.")
    void throwExceptionWhenTotalOver10() {
        PinNumbers pinNumbers = new PinNumbers();
        pinNumbers.record(new PinNumber(7));

        Assertions.assertThatThrownBy(() -> {
            pinNumbers.record(new PinNumber(4));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("1 ~ 9 프레임의 투구 상태를 알 수 있다.")
    void canKnowState_1_9() {
        PinNumbers pinNumbers = new PinNumbers();
        pinNumbers.record(new PinNumber(10));

        Assertions.assertThat(pinNumbers.state(1)).isEqualTo(FrameState.STRIKE.getFrameState());
    }

    @Test
    @DisplayName("10 프레임의 투구 상태를 알 수 있다.")
    void canKnowState_10() {
        PinNumbers pinNumbers = new PinNumbers();
        pinNumbers.record(new PinNumber(7));
        pinNumbers.record(new PinNumber(3));
        pinNumbers.record(new PinNumber(5));

        Assertions.assertThat(pinNumbers.state(2)).isEqualTo(FrameState.SPARE.getFrameState());
    }
}
