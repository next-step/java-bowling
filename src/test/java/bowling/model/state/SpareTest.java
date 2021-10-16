package bowling.model.state;

import org.junit.Test;

import bowling.model.Pin;

public class SpareTest {
    @Test(expected = IllegalArgumentException.class)
    public void 예외가_발생해야_한다(){
        new Spare(new Pin(9), new Pin(2));
    }
}
