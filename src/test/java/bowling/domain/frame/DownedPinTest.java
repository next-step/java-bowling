package bowling.domain.frame;

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
                () -> firstTry.fromPreviousPitch(6)
        ).isInstanceOf(InvalidDownedPinNumberException.class);
    }

    @Test
    @DisplayName("해당 시도가 10(스트라이크)일 때")
    void testIsStrike() {
        DownedPin firstTry = DownedPin.fromNumber(10);

        assertThat(firstTry.isStrike()).isTrue();
    }

    @Test
    @DisplayName("2개의 시도가 스페어를 만들 때")
    void testIsSpare() {
        DownedPin firstPitch = DownedPin.fromNumber(6);
        DownedPin secondPitch = firstPitch.fromPreviousPitch(4);

        assertThat(firstPitch.isSpare(secondPitch)).isTrue();
    }
}
