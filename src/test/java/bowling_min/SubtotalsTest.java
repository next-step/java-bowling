package bowling_min;

import bowling_step3.domain.Frame;
import bowling_step3.domain.Frames;
import bowling_step3.domain.Subtotals;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SubtotalsTest {
    @Test
    void name() {
        Frames frames = Frames.create();
        Frame first = frames.first();
        first.playManual(3);
        first.playManual(2);
//        Subtotals subtotals = new Subtotals();
//        subtotals.add(first.subtotal());
        assertThat(first.subtotal(frames)).isEqualTo(5);
        System.out.println(first.subtotal(frames));
    }

    @Test
    void name2() {
        Frames frames = Frames.create();
        Frame first = frames.first();
        first.playManual(3);
        first.playManual(7);
        System.out.println(first.subtotal(frames));
    }
}
