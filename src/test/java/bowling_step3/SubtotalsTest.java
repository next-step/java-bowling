package bowling_step3;

import bowling_step3.domain.Frame;
import bowling_step3.domain.Frames;
import bowling_step3.domain.Subtotals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SubtotalsTest {
    private Frames frames;
    private Frame frame;

    @BeforeEach
    public void setup() {
        frames = Frames.create();
        frame = frames.first();
    }

    @Test
    void spareWaitOnce() {
        frame.play(5)
                .play(5)
                .play(3);
        Subtotals subtotals = frames.first().createSubtotals();
        assertThat(subtotals).isEqualTo(new Subtotals(new LinkedList<>(List.of(13))));
    }

    @Test
    void strikeWaitTwice() {
        frame.play(10)
                .play(5)
                .play(5);
        Subtotals subtotals = frames.first().createSubtotals();
        assertThat(subtotals).isEqualTo(new Subtotals(new LinkedList<>(List.of(20))));
    }

    @Test
    void consecutiveTensShouldGive30() {
        frame.play(10)
                .play(10);
        Subtotals subtotals = frames.first().createSubtotals();
        assertThat(subtotals).isEqualTo(new Subtotals(new LinkedList<>(List.of(30))));
    }

    @Test
    void missGivesSubtotalWithoutWait() {
        frame.play(3)
                .play(4);
        Subtotals subtotals = frames.first().createSubtotals();
        assertThat(subtotals).isEqualTo(new Subtotals(new LinkedList<>(List.of(7))));
    }

    @Test
    void waitingSpareGivesNull() {
        frame.play(6)
                .play(4);
        Subtotals subtotals = frames.first().createSubtotals();
        assertThat(subtotals).isEqualTo(new Subtotals(new LinkedList<>(List.of())));
    }
}
