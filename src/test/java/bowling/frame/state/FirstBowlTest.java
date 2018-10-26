package bowling.frame.state;

import static org.junit.Assert.*;

import org.junit.Test;

public class FirstBowlTest {

    @Test
    public void bowlWhenSpare() {
        FirstBowl firstBowl = new FirstBowl(Pins.bowl(9));
        State state = firstBowl.bowl(1);
        assertTrue(state instanceof Spare);
    }

    @Test
    public void bowlWhenMiss() throws Exception {
        FirstBowl firstBowl = new FirstBowl(Pins.bowl(9));
        State state = firstBowl.bowl(0);
        assertTrue(state instanceof Miss);
    }

}
