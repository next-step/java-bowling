package bowling_min;

import bowling_step3.domain.Frame;
import bowling_step3.domain.Frames;
import bowling_step3.domain.Subtotals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SubtotalsTest {
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
    void name2() {
        frame = frame.play(3);
        Frame next = frame.play(7);
        assertThatThrownBy(() -> frame.subtotal(subtotals)).isInstanceOf(UnsupportedOperationException.class);
        next.play(1);
        assertThat(frame.subtotal(subtotals)).isEqualTo(11);
    }

    @Test
    void name3() {
        Frame next = frame.play(10);
        assertThatThrownBy(() -> frame.subtotal(subtotals)).isInstanceOf(UnsupportedOperationException.class);
        next = next.play(1);
        next = next.play(2);
        assertThat(frame.subtotal(subtotals)).isEqualTo(13);
    }
}
