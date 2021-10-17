package bowling.model.frame;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bowling.CannotBowlException;

public class NormalFrameTest {
    @Test
    public void 스트라이크이면_프레임은_끝난다() throws CannotBowlException {
        NormalFrame normalFrame = new NormalFrame(1);
        Frame next = normalFrame.bowl(10);
        assertTrue(normalFrame.isFinish());
        assertEquals(2, next.getNo());
    }

    @Test
    public void 투구2회시_프레임은_끝난다() throws CannotBowlException {
        NormalFrame normalFrame = new NormalFrame(1);
        Frame next = normalFrame.bowl(2).bowl(3);
        assertTrue(normalFrame.isFinish());
        assertEquals(2, next.getNo());
    }
}
