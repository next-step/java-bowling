package bowling.domain.pin;

import bowling.domain.score.ScoreType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalFramePinsTest {

    @Test
    void strike() {
        Pins pin = FinalFramePins.create();

        pin.down(10);
        pin.down(5);
        pin.down(5);

        assertThat(pin.getScoreType()).isEqualTo(ScoreType.STRIKE);
    }

    @Test
    void miss() {
        Pins pins = FinalFramePins.create();

        pins.down(3);
        pins.down(4);

        assertThat(pins.getScoreType()).isEqualTo(ScoreType.MISS);
    }

    @Test
    void spare() {
        Pins pins = FinalFramePins.create();

        pins.down(5);
        pins.down(5);
        pins.down(5);

        assertThat(pins.getScoreType()).isEqualTo(ScoreType.SPARE);
    }

    @Test
    void miss_down_third() {
        Pins pins = FinalFramePins.create();

        pins.down(3);
        pins.down(4);

        assertThatThrownBy(() -> pins.down(3))
                .isInstanceOf(IllegalStateException.class);
    }
}
