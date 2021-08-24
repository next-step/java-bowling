package bowling.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

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
    void 출력() {
        FirstPitch firstPitch = new FirstPitch(Pins.pitching(7));
        assertThat(firstPitch.display()).isEqualTo("7  |");
    }
}