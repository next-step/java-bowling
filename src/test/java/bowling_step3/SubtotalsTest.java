package bowling_step3;

import bowling_step3.domain.Frame;
import bowling_step3.domain.Frames;
import bowling_step3.domain.Player;
import bowling_step3.domain.Subtotals;
import bowling_step3.view.Output;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
    void spareWaitOnce() {
        frame.play(5)
                .play(5)
                .play(3);
        Subtotals subtotals = frames.first().createSubtotals();
        assertThat(subtotals).isEqualTo(new Subtotals(List.of(13)));
    }

    @Test
    void strikeWaitTwice() {
        frame.play(10)
                .play(5)
                .play(5);
        Subtotals subtotals = frames.first().createSubtotals();
        assertThat(subtotals).isEqualTo(new Subtotals(List.of(20)));
    }

    @Test
    void consecutiveTensShouldGive30() {
        frame.play(10)
                .play(10);
        Subtotals subtotals = frames.first().createSubtotals();
        assertThat(subtotals).isEqualTo(new Subtotals(List.of(30)));
    }

    @Test
    void missGivesSubtotalWithoutWait() {
        frame.play(3)
                .play(4);
        Subtotals subtotals = frames.first().createSubtotals();
        assertThat(subtotals).isEqualTo(new Subtotals(List.of(7)));
    }

    @Test
    void waitingSpareGivesNull() {
        frame.play(6)
                .play(4);
        Subtotals subtotals = frames.first().createSubtotals();
        assertThat(subtotals).isEqualTo(new Subtotals(List.of()));
    }
}
