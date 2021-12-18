package bowling.model.frame;

import bowling.model.state.State;
import bowling.model.state.Strike;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {

    @Test
    void stateTest() {
        State state = new Strike();
        assertTrue(state instanceof Strike);
    }

}
