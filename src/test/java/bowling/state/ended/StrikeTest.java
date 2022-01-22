package bowling.state.ended;

import bowling.state.Throwing;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {

    @Test
    void 쓰러트린_개수_심볼() {
        Throwing strike = new Strike();
        assertThat(strike.symbol()).isEqualTo("X");
    }
}