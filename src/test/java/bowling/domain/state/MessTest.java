package bowling.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.domain.Pins;
import bowling.exception.NotBowlingStateException;
import org.junit.jupiter.api.Test;

class MessTest {

    @Test
    void bowl_진행할시_예외처리() {
        assertThatExceptionOfType(NotBowlingStateException.class)
            .isThrownBy(() -> new Mess(Pins.of(2), Pins.of(2)).bowl(Pins.of(2)));
    }

    @Test
    void mess_아닌경우() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new Mess(Pins.of(8), Pins.of(2)));
    }

    @Test
    void 완료여부() {
        assertThat(new Mess(Pins.of(2), Pins.of(2)).isFinished()).isTrue();
    }

}