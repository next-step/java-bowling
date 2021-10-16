package bowling.model.state;

import bowling.CannotBowlException;
import org.junit.Test;

import bowling.model.Pin;

public class MissTest {
    @Test(expected = CannotBowlException.class)
    public void 예외가_발생해야_한다() throws CannotBowlException {
        new Miss(new Pin(1), new Pin(2)).bowl(3);
    }
}
