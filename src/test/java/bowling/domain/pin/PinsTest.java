package bowling.domain.pin;

import bowling.domain.state.StateExpression;
import bowling.exception.PinCountOutOfRangeException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PinsTest {

    @DisplayName("넘어진 볼링이 0~10 이외의 값이면 예외를 반환")
    @ParameterizedTest
    @ValueSource(ints = { -1, 11 })
    void validRange(final int count) {
        assertThatExceptionOfType(PinCountOutOfRangeException.class)
                .isThrownBy(() -> Pins.of(count));
    }

    @DisplayName("스트라이크(strike) 면 return true")
    @Test
    public void isStrike() {
        Pins pins = Pins.of(PinCount.MAX_COUNT);
        assertThat(pins.isStrike())
                .isTrue();
    }

    @DisplayName("스페어(spare) 면 return true")
    @ParameterizedTest
    @CsvSource({ "0,10", "1,9", "2,8" })
    public void isSpare(final int firstPinCount, final int secondPinCount) {
        assertThat(Pins.of(firstPinCount).isSpare(Pins.of(secondPinCount)))
                .isTrue();
    }

    @DisplayName("스페어(spare) 면 return true")
    @ParameterizedTest
    @CsvSource({ "0,1", "1,8", "2,3" })
    public void isMiss(final int firstPinCount, final int secondPinCount) {
        assertThat(Pins.of(firstPinCount).isMiss(Pins.of(secondPinCount)))
                .isTrue();
    }

    @DisplayName("각 상태에 해당하는 넘어진 핀의 개수를 문자열로 반환")
    @ParameterizedTest
    @MethodSource
    public void getHitCount(final Pins pins, final String expected) {
        assertThat(pins.getHitCount())
                .isEqualTo(expected);
    }

    private static Stream<Arguments> getHitCount() {
        return Stream.of(
                Arguments.of(Pins.of(0), StateExpression.GUTTER),
                Arguments.of(Pins.of(8), "8")
        );
    }
}
