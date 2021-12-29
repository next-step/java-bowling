package bowling.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.domain.Pins;
import org.junit.jupiter.api.Test;

class FirstBowlTest {

    @Test
    void mess() {
        assertThat(new FirstBowl(Pins.of(2)).bowl(Pins.of(2))).isInstanceOf(Mess.class);
    }

    @Test
    void spare() {
        assertThat(new FirstBowl(Pins.of(2)).bowl(Pins.of(8))).isInstanceOf(Spare.class);
    }

    @Test
    void isStrike() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new FirstBowl(Pins.of(10)));
    }

    @Test
    void isFinished() {
        assertThat(new FirstBowl(Pins.of(2)).isFinished()).isFalse();
    }

}