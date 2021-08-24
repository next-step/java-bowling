package bowling.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.Pins;
import org.junit.jupiter.api.Test;

class FirstPitchTest {
    @Test
    void 스페어_처리() {
        FirstPitch firstPitch = new FirstPitch(Pins.pitching(7));
        assertThat(firstPitch.nextPitch(3)).isInstanceOf(Spare.class);
    }

    @Test
    void 미스_처리() {
        FirstPitch firstPitch = new FirstPitch(Pins.pitching(7));
        assertThat(firstPitch.nextPitch(2)).isInstanceOf(Miss.class);
    }

    @Test
    void 출력_예외처리() {
        FirstPitch firstPitch = new FirstPitch(Pins.pitching(7));
        assertThatThrownBy(firstPitch::display).isInstanceOf(IllegalArgumentException.class);
    }
}