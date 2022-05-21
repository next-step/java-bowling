package bowling.domain.state;

import bowling.domain.pin.Pin;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    @Test
    void bowl() {
        assertThat(new Ready().bowl(Pin.of(10))).isInstanceOf(Strike.class);
        assertThat(new Ready().bowl(Pin.of(9))).isInstanceOf(FirstBowl.class);
    }
}
