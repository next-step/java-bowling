package bowling.model.state;

import org.junit.Test;

import bowling.CannotBowlException;
import bowling.model.Pin;

public class SpareTest {
    @Test(expected = IllegalArgumentException.class)
    public void 예외가_발생해야_한다(){
        new Spare(new Pin(9), new Pin(2));
    }

    @Test(expected = CannotBowlException.class)
    public void 더이상_투구를_할_수_없다() throws CannotBowlException {
        new Spare(new Pin(1), new Pin(9)).bowl(3);
    }
}
