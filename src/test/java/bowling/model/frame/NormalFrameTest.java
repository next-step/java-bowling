package bowling.model.frame;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bowling.model.CannotBowlException;

public class NormalFrameTest {
    @Test
    public void 스트라이크이면_프레임은_끝난다() throws CannotBowlException {
        NormalFrame normalFrame = new NormalFrame(1);
        Frame next = normalFrame.bowl(10);
        assertEquals(2, next.getNo());
    }

    @Test
    public void 투구2회시_프레임은_끝난다() throws CannotBowlException {
        NormalFrame normalFrame = new NormalFrame(1);
        Frame next = normalFrame.bowl(2).bowl(3);
        assertEquals(2, next.getNo());
    }

    @Test
    public void 보드_생성() throws Exception {
        Frame normalFrame = new NormalFrame(1);
        normalFrame
                .bowl(8).bowl(2)
                .bowl(9).bowl(1)
                .bowl(10)
                .bowl(10)
                .bowl(8).bowl(1)
                .bowl(7).bowl(3)
                .bowl(10)
                .bowl(10)
                .bowl(8).bowl(1)
                .bowl(10).bowl(8).bowl(2);
        Board board = normalFrame.createBoard();
        System.out.println(board);
    }
}
