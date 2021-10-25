package bowling.model.state;

import org.junit.Test;

import bowling.model.CannotBowlException;

public class MissTest {
    @Test(expected = CannotBowlException.class)
    public void 더이상_투구를_할_수_없다() throws CannotBowlException {
        new Miss(new Pin(1), new Pin(2)).bowl(3);
    }
}
