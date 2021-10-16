package bowling.model.state;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import bowling.model.Pin;

public class SecondBowlTest {
    @Test(expected = IllegalArgumentException.class)
    public void 예외가_발생해야_한다(){
        new SecondBowl(new Pin(9), new Pin(2));
    }

    @Test
    public void 상태가_ThirdSpare로_바뀌어야한다() {
        SecondBowl secondBowl = new SecondBowl(new Pin(9));
        State state = secondBowl.bowl(1);
        assertTrue(state instanceof ThirdSpare);
    }
}
