package bowling.state;

import bowling.model.state.FirstBowl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import bowling.model.state.Ready;
import bowling.model.state.State;
import bowling.model.state.Strike;

public class ReadyTest {
    @Test
    public void 상태가_스트라이크로_바뀌어야한다() {
        Ready ready = new Ready();
        State state = ready.bowl(10);
        Assertions.assertTrue(state instanceof Strike);
    }

    @Test
    public void 상태가_FirstBowl로_바뀌어야한다() {
        Ready ready = new Ready();
        State state = ready.bowl(9);
        Assertions.assertTrue(state instanceof FirstBowl);
    }
}
