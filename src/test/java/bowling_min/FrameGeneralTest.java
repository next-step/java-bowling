package bowling_min;

import bowling_step3.domain.Frame;
import bowling_step3.domain.FrameGeneral;
import bowling_step3.domain.Frames;
import bowling_step3.domain.State;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameGeneralTest {
    Frames frames = Frames.create();

    @Test
    public void doneWhenStrike() throws Exception {
        Frame frame = frames.first();
        frame.playManual(10, frames);
        assertThat(frame.subtotal().state()).isEqualTo(State.WAIT_TWICE);
    }

    @Test
    public void doneWhenSpare() throws Exception {
        Frame frame = frames.first();
        frame.playManual(8, frames);
        assertThat(frame.subtotal().state()).isEqualTo(State.INIT);
        frame.playManual(2, frames);
        assertThat(frame.subtotal().state()).isEqualTo(State.WAIT_ONCE);
    }

    @Test
    public void doneWhenMiss() throws Exception {
        Frame frame = frames.first();
        frame.playManual(8, frames);
        assertThat(frame.subtotal().state()).isEqualTo(State.INIT);
        frame.playManual(1, frames);
        assertThat(frame.subtotal().state()).isEqualTo(State.DONE);
    }

//    @Test
//    public void createBoard() throws Exception {
//        Frame normalFrame = new NormalFrame(1);
//        normalFrame
//                .bowl(8).bowl(2)
//                .bowl(9).bowl(1)
//                .bowl(10)
//                .bowl(10)
//                .bowl(8).bowl(1)
//                .bowl(7).bowl(3)
//                .bowl(10)
//                .bowl(10)
//                .bowl(8).bowl(1)
//                .bowl(10).bowl(8).bowl(2);
//        Board board = normalFrame.createBoard();
//        System.out.println(board);
//    }
}
