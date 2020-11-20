package bowling.domain.pin;

import bowling.domain.score.ScoreType2;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FinalFramePinsTest {

    @Test
    void miss() {
        Pins2 pins = FinalFramePins.create();
        pins.down(3);
        pins.down(4);

        assertThat(pins.getScoreType()).isEqualTo(ScoreType2.MISS);
    }

    @Test
    void spare() {
        Pins2 pins = FinalFramePins.create();
        pins.down(5);
        pins.down(5);
        pins.down(5);

        assertThat(pins.getScoreType()).isEqualTo(ScoreType2.SPARE);
    }
}
