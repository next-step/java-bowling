package bowling.model.state;

import org.junit.Test;

import bowling.CannotBowlException;

public class ThirdSpareTest {
    @Test(expected = CannotBowlException.class)
    public void 예외가_발생해야_한다() throws CannotBowlException {
        new ThirdSpare().bowl(3);
    }
}
