package bowling.model.state;

import bowling.model.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RunningTest {

    @Test
    @DisplayName("Running 상속한 클래스 isFinish false 리턴 테스트")
    void isFinishTest() {
        Ready ready = new Ready();
        assertFalse(ready.isFinish());
        FirstBowl firstBowl = new FirstBowl(Pins.knockedDown(2));
        assertFalse(firstBowl.isFinish());
    }
}
