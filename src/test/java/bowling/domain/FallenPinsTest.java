package bowling.domain;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import bowling.domain.exception.InvalidCountOfPinException;
import org.junit.jupiter.api.Test;

class FallenPinsTest {

    @Test
    void 쓰러진_볼링핀들_생성() {
        assertDoesNotThrow(() -> FallenPins.of(4));
    }

    @Test
    void 음수인_쓰러진_볼링핀들_생성_시도_시_예외_발생() {
        assertThatExceptionOfType(InvalidCountOfPinException.class)
                .isThrownBy(() -> FallenPins.of(-1));
    }

    @Test
    void 최대_볼링핀_수를_넘어서는_쓰러진_볼링핀들_생성_시도_시_예외_발생() {
        assertThatExceptionOfType(InvalidCountOfPinException.class)
                .isThrownBy(() -> FallenPins.of(15));
    }
}
