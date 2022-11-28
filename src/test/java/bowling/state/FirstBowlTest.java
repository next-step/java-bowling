package bowling.state;

import static org.assertj.core.api.Assertions.*;

import bowling.Pin;
import org.junit.jupiter.api.Test;

class FirstBowlTest {

    @Test
    void 스페어() {
        assertThat(new FirstBowl(Pin.from(9)).bowl(Pin.from(1)))
            .isInstanceOf(Spare.class);
    }

    @Test
    void 미스() {
        assertThat(new FirstBowl(Pin.from(3)).bowl(Pin.from(2)))
            .isInstanceOf(Miss.class);
    }
}
