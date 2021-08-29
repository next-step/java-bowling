package bowling.domain.state;

import bowling.domain.Pins;
import bowling.exception.NextPitchingException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SpareTest {
    @Test
    void 스페어_생성_예외() {
        Spare spare = new Spare(Pins.pitching(7), Pins.pitching(3));
        assertThatThrownBy(() -> {
            spare.nextPitch(3);
        }).isInstanceOf(NextPitchingException.class);
    }
}