package bowling.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.Pins;
import bowling.exception.NextPitchingException;
import org.junit.jupiter.api.Test;

class SpareTest {
    @Test
    void 스페어_생성_예외() {
        Spare spare = new Spare(Pins.pitching(7), Pins.pitching(3));
        assertThatThrownBy(() -> {
            spare.nextPitch(3);
        }).isInstanceOf(NextPitchingException.class);
    }

    @Test
    void 스페어_출력() {
        Spare spare = new Spare(Pins.pitching(7), Pins.pitching(3));
        assertThat(spare.display()).isEqualTo(" 7|/ |");
    }
}