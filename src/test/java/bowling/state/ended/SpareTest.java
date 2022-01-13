package bowling.state.ended;

import bowling.Pins;
import bowling.state.Throwing;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {

    @Test
    void 쓰러트린_개수_심볼() {
        Throwing spare = new Spare(new Pins(4));
        assertThat(spare.symbol()).isEqualTo("4|/");
    }
}