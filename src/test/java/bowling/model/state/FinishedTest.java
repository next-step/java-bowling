package bowling.model.state;

import bowling.model.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinishedTest {

    @Test
    @DisplayName("Finished 상속한 클래스 isFinish true 리턴 테스트")
    void isFinishTest() {
        Miss miss = new Miss(Pins.knockedDown(2), Pins.knockedDown(7));
        assertTrue(miss.isFinish());
        Spare spare = new Spare(Pins.knockedDown(2), Pins.knockedDown(8));
        assertTrue(spare.isFinish());
        Strike strike = new Strike();
        assertTrue(strike.isFinish());
    }
}
