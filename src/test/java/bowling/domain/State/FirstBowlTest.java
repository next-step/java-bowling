package bowling.domain.State;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FirstBowlTest {
    @DisplayName("첫번째 공을 굴렸을때 스페어인지 확인해본다")
    @Test
    void spareBowl() {
        FirstBowl firstBowl = new FirstBowl(Pins.bowl(8));
        State state = firstBowl.bowl(2);
        assertTrue(state instanceof Spare);
    }

    @DisplayName("첫번째 공을 굴렸을때 미스인지 확인해본다")
    @Test
    void missBowl() {
        FirstBowl firstBowl = new FirstBowl(Pins.bowl(7));
        State state = firstBowl.bowl(2);
        assertTrue(state instanceof Miss);
    }
}
