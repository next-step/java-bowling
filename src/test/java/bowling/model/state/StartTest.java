package bowling.model.state;

import bowling.model.Pins;
import bowling.model.state.finished.Strike;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StartTest {
    private static final Pins TEN_PINS = Pins.from(10);
    private static final Pins NINE_PINS = Pins.from(9);

    @Test
    void bowling_스트라이크() {
        assertThat(Start.bowling(TEN_PINS)).isInstanceOf(Strike.class);
    }

    @Test
    void bowling_오픈() {
        assertThat(Start.bowling(NINE_PINS)).isInstanceOf(Open.class);
    }
}