package bowling.domain.frame;

import bowling.bowlingexception.IllegalPinRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DownedPinTest {

    @ValueSource(ints = {-1, 11})
    @ParameterizedTest
    @DisplayName("1회 시도에 허용되지 않은 범위의 핀")
    void exceptionWhenInvalidRange(int input) {
        assertThatThrownBy(
                () -> DownedPin.fromNumber(input)
        ).isInstanceOf(IllegalPinRangeException.class);
    }

    @Test
    @DisplayName("추가적인 시도에 10을 초과할 수 없음")
    void exceptionWithAdditionalPitch() {
        DownedPin pin = DownedPin.fromNumber(6);

        // 6 + 4는 허용된다.
        pin.fromPreviousPitch(4);

        // 6 + 5는 10을 초과하므로 허용되지 않는다.
        assertThatThrownBy(
                () -> pin.fromPreviousPitch(5)
        ).isInstanceOf(IllegalPinRangeException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"10:true", "4:false"}, delimiter = ':')
    @DisplayName("해당 핀이 단독으로 10을 가리킬 경우 Stirke 조건을 만족함")
    void isStrike(int number, boolean expected) {
        DownedPin pin = DownedPin.fromNumber(number);

        assertThat(pin.isStrike()).isEqualTo(expected);
    }

    @Test
    @DisplayName("두 핀이 스페어 조건(1차 + 2차 = 10)을 만족함")
    void isSpare() {
        DownedPin firstPitch = DownedPin.fromNumber(6);
        DownedPin secondPitch = firstPitch.fromPreviousPitch(4);

        assertThat(firstPitch.isSpare(secondPitch)).isTrue();
    }
}
