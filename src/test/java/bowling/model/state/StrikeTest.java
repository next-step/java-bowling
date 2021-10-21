package bowling.model.state;

import org.junit.Test;

import bowling.model.CannotBowlException;

public class StrikeTest {
    @Test(expected = CannotBowlException.class)
    public void 더이상_투구를_할_수_없다() throws CannotBowlException {
        new Strike().bowl(3);
    }
}
