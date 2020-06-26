package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.ScoreType;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
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

    @DisplayName("strike후에 플레이 하면 예외 발생한다.")
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
            .isEqualTo(Optional.of(ScoreType.MISS));
    }

    @DisplayName("3번의 플레이를 하면 예외가 발생한다.")
    @Test
    void third_play_then_exception() {
        Pins pins = new NormalPins();
        pins.down(8);
        pins.down(1);

        assertThatThrownBy(() -> pins.down(1))
            .isInstanceOf(IllegalStateException.class);
    }

}
