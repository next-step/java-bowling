package bowling.model.state;


import bowling.model.Pins;
import bowling.model.state.finished.Strike;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StatesTest {
    private static final Pins TEN_PINS = Pins.from(10);
    @Test
    void bowling() {
        States states = new States();
        states.bowling(TEN_PINS);
        assertThat(states.last()).isInstanceOf(Strike.class);
    }

    @Test
    void isFinished() {
        States states = new States();
        assertThat(states.isFinished()).isFalse();

        states.bowling(TEN_PINS);
        assertThat(states.isFinished()).isTrue();
    }

    @Test
    void isMaxScore() {
        States states = new States();
        states.bowling(TEN_PINS);
        assertThat(states.isFinished()).isTrue();
    }
}