package bowling.domain;

import bowling.bowlingexception.InvalidDownedPinNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
}
