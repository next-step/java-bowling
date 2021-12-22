package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrameTest {

    @Test
    @DisplayName("해당 프레임이 NormalFrame인지 체크하는 메서드 테스트")
    void isNormalFrameTest() {
        NormalFrame normalFrame = new NormalFrame(1);
        assertTrue(normalFrame.isNormalFrame());
        FinalFrame finalFrame = new FinalFrame(10);
        assertFalse(finalFrame.isNormalFrame());
    }
}
