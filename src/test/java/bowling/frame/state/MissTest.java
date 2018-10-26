package bowling.frame.state;

import org.junit.Test;

public class MissTest {

    @Test(expected = UnsupportedOperationException.class)
    public void bowl() {
        Ready ready = new Ready();
        ready.bowl(8).bowl(1).bowl(10);
    }

}
