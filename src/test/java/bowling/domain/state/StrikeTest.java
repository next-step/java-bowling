package bowling.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import bowling.domain.Pins;
import org.junit.jupiter.api.Test;

class StrikeTest {
    @Test
    void 출력_테스트() {
        Strike strike = new Strike(Pins.pitching(10));
        assertThat(strike.display()).isEqualTo("  X  |");
    }

    @Test
    void 예외_테스트() {
        Strike strike = new Strike(Pins.pitching(10));
        assertThatThrownBy(() -> {
            strike.nextPitch(1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}