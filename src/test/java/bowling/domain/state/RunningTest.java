package bowling.domain.state;

import bowling.domain.pin.FallenPin;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RunningTest {

    @Test
    void isFinished() {
        assertThat(running().isFinished()).isFalse();
    }

    private static Running running() {
        return new FirstBowling(FallenPin.of(9));
    }
}
