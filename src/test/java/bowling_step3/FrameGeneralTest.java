package bowling_step3;

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
        frame.play(10);
        assertThat(frame.state()).isEqualTo(State.WAIT_TWICE);
    }

    @Test
    public void doneWhenSpare() throws Exception {
        Frame frame = frames.first();
        frame.play(8);
        assertThatThrownBy(() -> frame.state()).isInstanceOf(UnsupportedOperationException.class);
        frame.play(2);
        assertThat(frame.state()).isEqualTo(State.WAIT_ONCE);
    }

    @Test
    public void doneWhenMiss() throws Exception {
        Frame frame = frames.first();
        frame.play(8);
        assertThatThrownBy(() -> frame.state()).isInstanceOf(UnsupportedOperationException.class);
        frame.play(1);
        assertThat(frame.state()).isEqualTo(State.DONE);
    }


    @Test
    void printSubtotalsWithFives() {
        Frame frame = frames.first();
        while (!(frame.next() == null && frame.done())) {
            frame = frame.play(5);
            Subtotals subtotals = frames.first().createSubtotals();
            Output.printFrames(10, frames, new Player("tst"));
            Output.printSubtotals(subtotals, new Player("tst"));
        }
    }
}
