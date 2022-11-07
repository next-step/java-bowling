package bowling.domain.status;

import bowling.domain.Pin;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReadyTest {

    @Test
    void strike() {
        assertThat(new Ready().bowl(new Pin(10))).isInstanceOf(Strike.class);
    }

    @Test
    void firstBowl() {
        assertThat(new Ready().bowl(new Pin(3))).isInstanceOf(FirstBowl.class);
    }
}