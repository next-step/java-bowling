package bowling.state;

import bowling.Pins;
import bowling.state.running.FirstBowl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FirstBowlTest {

    @Test
    void 쓰러트린_핀_개수_심볼() {
        Throwing firstBowl = new FirstBowl(new Pins(4));
        assertThat(firstBowl.symbol()).isEqualTo("4");
    }
}