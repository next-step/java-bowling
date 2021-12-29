package bowling.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.domain.Pins;
import org.junit.jupiter.api.Test;

class SpareTest {

    @Test
    void create() {
        assertThat(new Spare(Pins.of(8), Pins.of(2))).isInstanceOf(Spare.class);
    }

    @Test
    void notSpare() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new Spare(Pins.of(8), Pins.of(1)));
    }

    @Test
    void isFinished() {
        assertThat(new Spare(Pins.of(8), Pins.of(2)).isFinished()).isTrue();
    }

}