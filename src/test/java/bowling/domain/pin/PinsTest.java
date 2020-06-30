package bowling.domain.pin;

import bowling.domain.score.Score;
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
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
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

    @DisplayName("각 상태에 해당하는 넘어진 핀의 개수 반환")
    @ParameterizedTest
    @MethodSource
    public void getHitCount(final Pins pins, final int expected) {
        assertThat(pins.getHitCount())
                .isEqualTo(expected);
    }

    private static Stream<Arguments> getHitCount() {
        return Stream.of(
                Arguments.of(Pins.of(0), 0),
                Arguments.of(Pins.of(8), 8)
        );
    }

    @DisplayName("현재 볼링 핀의 개수에 두 번째 볼링 핀의 개수를 더하여 핀 개수를 반환")
    @ParameterizedTest
    @MethodSource
    public void totalPins(final Pins pins, final Pins secondPins, final PinCount expected) {
        assertThat(pins.totalPins(secondPins))
                .isEqualTo(expected);
    }

    private static Stream<Arguments> totalPins() {
        return Stream.of(
                Arguments.of(Pins.of(0), Pins.of(10), PinCount.of(PinCount.MAX_COUNT)),
                Arguments.of(Pins.of(8), Pins.of(1), PinCount.of(9))
        );
    }

    @DisplayName("인자로 넘어온 점수에 해당 볼링 핀의 개수를 더한 점수를 반환")
    @ParameterizedTest
    @MethodSource
    public void sumScore(final Pins pins, final Score score, final Score expected) {
        assertThat(pins.sumScore(score))
                .isEqualTo(expected);
    }

    private static Stream<Arguments> sumScore() {
        return Stream.of(
                Arguments.of(Pins.of(0), Score.valueOf(4, 0), Score.valueOf(4, -1)),
                Arguments.of(Pins.of(4), Score.valueOf(6, 0), Score.valueOf(10, -1))
        );
    }
}
