package bowling.model.state;


import bowling.model.state.finished.Strike;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StatesTest {

    @Test
    void bowling() {
        States states = new States();
        states.bowling(10);
        assertThat(states.last()).isInstanceOf(Strike.class);
    }

    @Test
    void isFinished() {
        States states = new States();
        assertThat(states.isFinished()).isFalse();

        states.bowling(10);
        assertThat(states.isFinished()).isTrue();
    }

    @Test
    void isMaxScore() {
        States states = new States();
        states.bowling(10);
        assertThat(states.isFinished()).isTrue();
    }
}