package bowling;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class BowlingPinTest {

    @Test
    void createFailed() {
        assertThatThrownBy(() -> new BowlingPin(BowlingPin.MAX_PIN_NUMBER + 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("볼링핀의 갯수는 0~10사이여야만 합니다.");
    }

    @Test
    void hitPins() {
        BowlingPin bowlingPin = new BowlingPin(10);
        assertThat(bowlingPin.hitPins(new BowlingPin(5))).isEqualTo(new BowlingPin(5));
    }
}