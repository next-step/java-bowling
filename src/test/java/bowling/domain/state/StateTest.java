package bowling.domain.state;

import bowling.domain.Pin;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StateTest {

    @Test
    void strikeStateTest() {
        State state = new Strike();
        assertThat(state).isInstanceOf(Strike.class);
    }

    @Test
    void strikeStateRecordTest() {
        State state = new Strike();
        assertThat(state.record()).isEqualTo("X");
    }

    @Test
    void strikeStateIsFinishTest() {
        State state = new Strike();
        assertTrue(state.isFinish());
    }

    @Test
    void spareStateTest() {
        State state = new Spare(new Pin(5),new Pin(5));
        assertThat(state).isInstanceOf(Spare.class);
    }

    @Test
    void spareStateRecordTest() {
        State state = new Spare(new Pin(5),new Pin(5));
        assertThat(state.record()).isEqualTo("5|/");
    }

    @Test
    void spareStateIsFinishTest() {
        State state = new Spare(new Pin(5),new Pin(5));
        assertTrue(state.isFinish());
    }

}
