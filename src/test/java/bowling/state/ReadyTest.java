package bowling.state;

import static org.assertj.core.api.Assertions.*;

import bowling.Pin;
import org.junit.jupiter.api.Test;

class ReadyTest {

    @Test
    void 스트라이크() {
        assertThat(new Ready().bowl(Pin.from(10)))
            .isInstanceOf(Strike.class);
    }

    @Test
    void 첫번째_볼() {
        assertThat(new Ready().bowl(Pin.from(9)))
            .isInstanceOf(FirstBowl.class);
    }
}
