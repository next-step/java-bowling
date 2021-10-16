package bowling.model.state;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecondStrikeTest {
    @Test
    public void 상태가_ThirdStrike로_바뀌어야한다() {
        SecondStrike secondStrike = new SecondStrike();
        State state = secondStrike.bowl(10);
        assertTrue(state instanceof ThirdStrike);
    }
}
