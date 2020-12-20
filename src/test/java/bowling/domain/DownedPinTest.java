package bowling.domain;

import bowling.bowlingexception.InvalidDownedPinNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DownedPinTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    @DisplayName("0 ~ 10 까지의 범위 이외에 대한 예외처리")
    void testRangeValidation(int input) {
        assertThatThrownBy(
                () -> DownedPin.fromNumber(input)
        ).isInstanceOf(InvalidDownedPinNumberException.class);
    }

    @Test
    @DisplayName("한 프레임 안에서 2번 째 시도가 정상적이지 않을 때의 검증")
    void testFrameCondition() {
        DownedPin firstTry = DownedPin.fromNumber(5);

        assertThatThrownBy(
                () -> firstTry.fromSubordinateTry(DownedPin.fromNumber(6))
        ).isInstanceOf(InvalidDownedPinNumberException.class);
    }

    @Test
    @DisplayName("스트라이크")
    void testIsStrike() {
        assertThat(DownedPin.fromNumber(10).isStrike())
                .isTrue();
    }

    private static Stream<Arguments> makeSpareConditions() {

        return Stream.of(
                Arguments.of(DownedPin.fromNumber(3), DownedPin.fromNumber(7), true),
                Arguments.of(DownedPin.fromNumber(10), DownedPin.fromNumber(0), false)
        );
    }

    @MethodSource("makeSpareConditions")
    @ParameterizedTest
    @DisplayName("스페어 여부 확인")
    void testIsSpare(DownedPin arg1, DownedPin arg2, boolean result) {
        assertThat(arg1.isSpare(arg2)).isEqualTo(result);
    }
}
