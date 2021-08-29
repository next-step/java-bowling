package bowling.domain.state;

import bowling.domain.Pins;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StrikeTest {
    @Test
    void 예외_테스트() {
        Strike strike = new Strike(Pins.pitching(10));
        assertThatThrownBy(() -> {
            strike.nextPitch(1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}