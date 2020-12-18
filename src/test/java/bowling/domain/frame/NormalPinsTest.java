package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.score.ScoreType;
import org.junit.jupiter.api.Test;

class NormalPinsTest {

    @Test
    void invalid_negative() {
        NormalPins normalPins = new NormalPins();

        assertThatThrownBy(() -> normalPins.down(-1))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void invalid_more_than_10() {
        NormalPins normalPins = new NormalPins();

        assertThatThrownBy(() -> normalPins.down(11))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void invalid_sum_more_than_10() {
        NormalPins normalPins = new NormalPins();
        normalPins.down(9);

        assertThatThrownBy(() -> normalPins.down(2))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void strike_and_play_then_throw_exception() {
        NormalPins normalPins = new NormalPins();

        normalPins.down(10);
        assertThatThrownBy(() -> normalPins.down(1))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void two_play() {
        Pins pins = new NormalPins();
        pins.down(8);
        pins.down(1);

        assertThat(pins.getScoreType())
            .isEqualTo(ScoreType.MISS);
    }

    @Test
    void third_play_then_exception() {
        Pins pins = new NormalPins();
        pins.down(8);
        pins.down(1);

        assertThatThrownBy(() -> pins.down(1))
            .isInstanceOf(IllegalStateException.class);
    }
}
