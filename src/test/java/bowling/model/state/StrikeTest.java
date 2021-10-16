package bowling.model.state;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StrikeTest {
    @Test
    public void 상태가_SecondStrike로_바뀌어야한다() {
        Strike strike = new Strike();
        State state = strike.bowl(10);
        assertTrue(state instanceof SecondStrike);
    }
}
