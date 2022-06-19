package bowling_min;

import bowling_step3.domain.*;
import bowling_step3.view.Output;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameGeneralTest {
    private Frames frames;

    @BeforeEach
    public void setup() {
        frames = Frames.create();
    }

    @Test
    public void doneWhenStrike() throws Exception {
        Frame frame = frames.first();
        frame.playManual(10);
        assertThat(frame.state()).isEqualTo(State.WAIT_TWICE);
    }

    @Test
    public void doneWhenSpare() throws Exception {
        Frame frame = frames.first();
        frame.playManual(8);
        assertThatThrownBy(() -> frame.state()).isInstanceOf(UnsupportedOperationException.class);
        frame.playManual(2);
        assertThat(frame.state()).isEqualTo(State.WAIT_ONCE);
    }

    @Test
    public void doneWhenMiss() throws Exception {
        Frame frame = frames.first();
        frame.playManual(8);
        assertThatThrownBy(() -> frame.state()).isInstanceOf(UnsupportedOperationException.class);
        frame.playManual(1);
        assertThat(frame.state()).isEqualTo(State.DONE);
    }

    @Test
    public void createBoard() throws Exception {
        Frame frame = frames.first();
        frame
                .playManual(8).playManual(2)
                .playManual(9).playManual(1)
                .playManual(10)
                .playManual(10)
                .playManual(8).playManual(1)
                .playManual(7).playManual(3)
                .playManual(10)
                .playManual(10)
                .playManual(8).playManual(1)
                .playManual(10).playManual(8).playManual(2);
        System.out.println(frames);
        Subtotals subtotals = frame.createSubtotals();
        Output.printFrames(10, frames, new Player("tst"));
        System.out.println(subtotals);
    }

    @Test
    void name9() {
        Frame frame =frames.first();
        while (!(frame.next() == null && frame.done())) {
//            Frame sub = frame;
            frame = frame.playManual(5);
            Subtotals subtotals = frames.first().createSubtotals();
            System.out.println(subtotals);
        }
        Output.printFrames(10, frames, new Player("tst"));
    }
}
