package bowling.domain.state;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class ReadyTest {

    @Test
    void strike_in_ready_state(){
        Ready ready = new Ready();
        State state = ready.pitch(10);
        assertTrue(state instanceof Strike);
    }

    @Test
    void after_first_pitch_in_ready_state(){
        Ready ready = new Ready();
        State state = ready.pitch(3);
        assertTrue(state instanceof FirstBowl);
    }
}
