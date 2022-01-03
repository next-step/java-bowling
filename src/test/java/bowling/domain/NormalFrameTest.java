package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import bowling.exception.NotBowlingStateException;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

    @Test
    void 완료인경우() {
        assertAll(() -> assertTrue(NormalFrame.first().bowl(Pins.of(10)).isFinished()),
            () -> assertTrue(NormalFrame.first().bowl(Pins.of(6)).bowl(Pins.of(4)).isFinished()),
            () -> assertTrue(NormalFrame.first().bowl(Pins.of(6)).bowl(Pins.of(3)).isFinished())
        );
    }

    @Test
    void 미완료인경우() {
        assertThat(NormalFrame.first().bowl(Pins.of(9)).isFinished()).isFalse();
    }

    @Test
    void bowl_횟수초과() {
        assertThatExceptionOfType(NotBowlingStateException.class)
            .isThrownBy(() -> NormalFrame.first().bowl(Pins.of(3)).bowl(Pins.of(3)).bowl(Pins.of(3)));
    }

}