package bowling_step3;

import bowling_step3.domain.*;
import bowling_step3.domain.state.Done;
import bowling_step3.domain.state.FirstPitch;
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
        assertThat(frame.status()).isInstanceOf(Done.class);
    }

    @Test
    public void doneWhenSpare() throws Exception {
        Frame frame = frames.first();
        frame.play(8);
        frame.play(2);
        assertThat(frame.status()).isInstanceOf(Done.class);
    }

    @Test
    public void doneWhenMiss() throws Exception {
        Frame frame = frames.first();
        frame.play(8);
        frame.play(1);
        assertThat(frame.status()).isInstanceOf(Done.class);
    }

    @Test
    void printSubtotalsWithFives() {
        Frame frame = frames.first();
        while (!(frame.next() == null && frame.status().isFinished())) {
            frame = frame.play(5);
            Subtotals subtotals = frames.first().createSubtotals();
            Output.printFrames(10, frames, new Player("tst"));
            Output.printSubtotals(subtotals, new Player("tst"));
        }
    }
}
