package bowling_step3;

import bowling_step3.domain.Frame;
import bowling_step3.domain.Frames;
import bowling_step3.domain.Player;
import bowling_step3.domain.Subtotals;
import bowling_step3.view.Output;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class E2ETest {
    private Frames frames;
    private Frame frame;

    @BeforeEach
    public void setup() {
        frames = Frames.create();
        frame = frames.first();
    }

    @Test
    public void roll_strike() throws Exception {
        frame = frame.play(10);
        assertThat(frames.get(0).scores().getFirstScore()).isEqualTo(10);
        frame = frame.play(8);
        assertThat(frames.get(1).scores().getFirstScore()).isEqualTo(8);
    }

    @Test
    public void roll_spare() throws Exception {
        frame = frame.play(9);
        frame = frame.play(1);
        assertThat(frames.get(0).scores().scores()).isEqualTo(Arrays.asList(9, 1));
        frame = frame.play(10);
        assertThat(frames.get(1).scores().getFirstScore()).isEqualTo(10);
    }

    @Test
    public void roll_miss() throws Exception {
        frame.play(8);
        assertThat(frames.first().scores().getFirstScore()).isEqualTo(8);
        frame.play(1);
        assertThat(frames.first().scores().scores()).isEqualTo(Arrays.asList(8, 1));
    }

    @Test
    public void all_strike() throws Exception {
        for (int i = 0; i < 12; i++) {
            frame = frame.play(10);
        }
        assertThat(frames.last().scores().scores()).isEqualTo(Arrays.asList(10, 10, 10));
    }

    @Test
    public void createSubtotals() throws Exception {
        Frame frame = frames.first();
        frame
                .play(8).play(2)
                .play(9).play(1)
                .play(10)
                .play(10)
                .play(8).play(1)
                .play(7).play(3)
                .play(10)
                .play(10)
                .play(8).play(1)
                .play(10).play(8).play(2);
        Subtotals subtotals = frame.createSubtotals();
        Player player = new Player("tst", frames);
        Output.printFrames(10, player);
        Output.printSubtotals(subtotals, player);
    }
}
