package bowling.state.ended;

import bowling.Pins;
import bowling.state.Throwing;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MissTest {

    @Test
    void 쓰러트린_개수_심볼() {
        Throwing miss = new Miss(new Pins(4), new Pins(3));
        assertThat(miss.symbol()).isEqualTo("4|3");
    }
}