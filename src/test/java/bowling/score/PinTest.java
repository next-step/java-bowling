package bowling.score;

import bowling.global.exception.InputPinsNullPointerException;
import bowling.global.exception.OutOfPinsRangeException;
import bowling.global.utils.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PinTest {

    private Pin pin;

    @Test
    @DisplayName("Pin을 몇개 쓰려트렸는지 확인")
    void falledPins() {
        pin = Pin.bowl("5");
        assertThat(pin.getFalledPins()).isEqualTo(5);
    }

    @Test
    @DisplayName("스트라이크")
    void isStrike() {
        pin = Pin.bowl("10");
        assertThat(pin.isStrike()).isTrue();
    }

    @Test
    @DisplayName("스페어")
    void isSpare() {
        pin = Pin.bowl("8");
        assertThat(pin.isSpare(2)).isTrue();
    }

    @Test
    @DisplayName("거터")
    void isGutter() {
        pin = Pin.bowl("0");
        assertThat(pin.isGutter()).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("입력값이 없을 경우 Exception 발생")
    void validateFalledPinsIsNull(String falledPins) {
        assertThatExceptionOfType(InputPinsNullPointerException.class)
                .isThrownBy(() -> pin = Pin.bowl(falledPins)
                ).withMessage(ExceptionMessage.INVALID_PITCH_BALL_IS_NULL);
    }

    @Test
    @DisplayName("입력값이 0 ~ 10의 범위를 벗어난 경우 Exception 발생")
    void validateFalledPinsRange() {
        assertThatExceptionOfType(OutOfPinsRangeException.class)
                .isThrownBy(() -> pin = Pin.bowl("11")
                ).withMessage(ExceptionMessage.INVALID_PITCH_RANGE);
    }

}
