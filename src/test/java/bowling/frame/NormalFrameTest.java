package bowling.frame;

import static org.junit.Assert.*;

import org.junit.Test;

public class NormalFrameTest {
    @Test
    public void bowlWhenStrike() throws Exception {
        NormalFrame normalFrame = new NormalFrame(1);
        Frame next = normalFrame.bowl(10);
        assertEquals(2, next.getNo());
    }

    @Test
    public void bowlWhenSpare() throws Exception {
        NormalFrame normalFrame = new NormalFrame(1);
        Frame next = normalFrame.bowl(8);
        assertEquals(1, next.getNo());
        next = normalFrame.bowl(2);
        assertEquals(2, next.getNo());
    }

    @Test
    public void bowlWhenMiss() throws Exception {
        NormalFrame normalFrame = new NormalFrame(1);
        Frame next = normalFrame.bowl(8);
        assertEquals(1, next.getNo());
        next = normalFrame.bowl(1);
        assertEquals(2, next.getNo());
    }

    @Test
    public void createBoard() throws Exception {
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
