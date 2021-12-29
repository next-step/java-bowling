package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class NormalFrameTest {

    @Test
    void isFinished() {
        assertAll(() -> assertTrue(NormalFrame.first().bowl(Pins.of(10)).isFinished()),
            () -> assertTrue(NormalFrame.first().bowl(Pins.of(6)).bowl(Pins.of(4)).isFinished()),
            () -> assertTrue(NormalFrame.first().bowl(Pins.of(6)).bowl(Pins.of(3)).isFinished())
        );
    }

    @Test
    void isNotFinished() {
        assertThat(NormalFrame.first().bowl(Pins.of(9)).isFinished()).isFalse();
    }

    @Test
    void validException() {
        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> NormalFrame.first().bowl(Pins.of(3)).bowl(Pins.of(3)).bowl(Pins.of(3)));
    }

}