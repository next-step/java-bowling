package bowling.domain.status;

import bowling.domain.Pin;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FirstBowlTest {

    @Test
    void spare() {
        Pin first = new Pin(7);
        assertThat(new FirstBowl(first).bowl(new Pin(3))).isInstanceOf(Spare.class);
    }

    @Test
    void miss() {
        Pin first = new Pin(7);
        assertThat(new FirstBowl(first).bowl(new Pin(1))).isInstanceOf(Miss.class);
    }
}