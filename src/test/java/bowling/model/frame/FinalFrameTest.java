package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {

    @Test
    @DisplayName("한번 스트라이크로 finish 된 후에 상태가 reset 되 더 굴릴 수 있는 것 확인")
    void afterFinishCanBowlAgainTest() {
        FinalFrame finalFrame = new FinalFrame(10);
        finalFrame.bowl(10);
        assertTrue(finalFrame.isFinish());
        finalFrame.bowl(5);
    }
}
