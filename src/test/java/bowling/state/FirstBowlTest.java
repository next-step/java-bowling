package bowling.state;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import bowling.model.state.FirstBowl;
import bowling.model.state.Miss;
import bowling.model.state.Spare;
import bowling.model.state.State;

public class FirstBowlTest {
    @Test
    public void 상태가_스페어로_바뀌어야한다() {
        FirstBowl firstBowl = new FirstBowl(9);
        State state = firstBowl.bowl(1);
        Assertions.assertTrue(state instanceof Spare);
    }

    @Test
    public void 상태가_미로_바뀌어야한다() {
        FirstBowl firstBowl = new FirstBowl(2);
        State state = firstBowl.bowl(1);
        Assertions.assertTrue(state instanceof Miss);
    }
}
