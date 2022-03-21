package bowling.frame;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    @Test
    public void bowlWhenStrike() throws Exception {
        NormalFrame normalFrame = new NormalFrame(1);
        Frame next = normalFrame.bowl(10);
        assertThat(next.getNo()).isEqualTo(2);
    }

    @Test
    public void bowlWhenSpare() throws Exception {
        NormalFrame normalFrame = new NormalFrame(1);
        Frame next = normalFrame.bowl(8);
        assertThat(next.getNo()).isEqualTo(1);
        next = normalFrame.bowl(2);
        assertThat(next.getNo()).isEqualTo(2);
    }

    @Test
    public void bowlWhenMiss() throws Exception {
        NormalFrame normalFrame = new NormalFrame(1);
        Frame next = normalFrame.bowl(8);
        assertThat(next.getNo()).isEqualTo(1);
        next = normalFrame.bowl(1);
        assertThat(next.getNo()).isEqualTo(2);
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
