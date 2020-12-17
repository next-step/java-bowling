package bowling.model.state;

import bowling.model.state.finished.Strike;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StartTest {

    @Test
    void bowling_스트라이크() {
        Start start = new Start();
        assertThat(start.bowling(10)).isInstanceOf(Strike.class);
    }

    @Test
    void bowling_오픈() {
        Start start = new Start();
        assertThat(start.bowling(9)).isInstanceOf(Open.class);
    }
}