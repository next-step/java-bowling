package bowling_step3;

import bowling_step3.domain.Frame;
import bowling_step3.domain.Frames;
import bowling_step3.domain.Subtotals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class E2ETest {
    private Frames frames;
    private Frame frame;
    private Subtotals subtotals;

    @BeforeEach
    public void setup() {
        frames = Frames.create();
        frame = frames.first();
        subtotals = new Subtotals();
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

}
