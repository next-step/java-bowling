package bowling.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.domain.Pins;
import bowling.exception.NotBowlingStateException;
import org.junit.jupiter.api.Test;

class MessTest {

    @Test
    void bowl() {
        assertThatExceptionOfType(NotBowlingStateException.class)
            .isThrownBy(() -> new Mess(Pins.of(2), Pins.of(2)).bowl(Pins.of(2)));
    }

    @Test
    void notMess() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new Mess(Pins.of(8), Pins.of(2)));
    }

    @Test
    void isFinished() {
        assertThat(new Mess(Pins.of(2), Pins.of(2)).isFinished()).isTrue();
    }

}