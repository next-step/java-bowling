package bowling.domain.state;

import bowling.domian.state.Pins;
import bowling.domian.state.exception.InvalidPinCountException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class PinsTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    @DisplayName("투구 입력 값이 유효하지 않은 경우 Exception")
    public void throwInvalidInputException(int pinCount) {
        assertThatExceptionOfType(InvalidPinCountException.class).isThrownBy(
                () -> Pins.bowl(pinCount)
        );
    }

    @Test
    @DisplayName("투구 결과 Strike 확인")
    public void checkStrikePinCount() {
        Pins pins = Pins.bowl(10);

        assertThat(pins.isStrike()).isTrue();
    }

    @Test
    @DisplayName("투구 결과 Spare 확인")
    public void checkSparePinCount() {
        Pins firstPins = Pins.bowl(3);
        Pins secondPins = firstPins.secondBowl(7);

        assertThat(firstPins.isSpare(secondPins)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 8, 10})
    @DisplayName("두번째 투구 결과 값이 유효하지 않은 경우 Exception")
    public void throwInvalidSecondInputException(int secondPinCount) {
        Pins firstPins = Pins.bowl(3);

        assertThatExceptionOfType(InvalidPinCountException.class).isThrownBy(
                () ->  firstPins.secondBowl(secondPinCount)
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"0,0", "9,0", "2,5"})
    @DisplayName("투구 결과 Miss 확인")
    public void checkMissPinCount(int firstPinCount, int secondPinCount) {
        Pins first = Pins.bowl(firstPinCount);
        Pins second = first.secondBowl(secondPinCount);

        assertThat(first.isMiss(second)).isTrue();
    }
}
