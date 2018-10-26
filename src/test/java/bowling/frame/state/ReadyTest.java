package bowling.frame.state;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReadyTest {
    @Test
    public void bowlWhenStrike() throws Exception {
        Ready ready = new Ready();
        State state = ready.bowl(10);
        assertTrue(state instanceof Strike);
    }

    @Test
    public void bowlWhenFirst() throws Exception {
        Ready ready = new Ready();
        State state = ready.bowl(9);
        assertTrue(state instanceof FirstBowl);
    }
}
