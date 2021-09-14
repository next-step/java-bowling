package bowling.domain.frame;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TotalTest {
    @Test
    void normalFrame_yet() {
        Frame frame = new NormalFrame().next(1);
        assertThat(frame.total()).isEqualTo(0);
    }

    @Test
    void normalFrame_miss() {
        Frame frame = new NormalFrame().next(1).next(1);
        assertThat(frame.total()).isEqualTo(2);
    }

    @Test
    void normalFrame_spare_yet() {
        Frame frame = new NormalFrame().next(1).next(9);
        assertThat(frame.total()).isEqualTo(0);
    }

    @Test
    void normalFrame_spare() {
        Frame frame = new NormalFrame().next(1).next(9);
        frame.next(1);
        assertThat(frame.total()).isEqualTo(11);
    }

    @Test
    void normalFrame_strike_yet() {
        Frame frame = new NormalFrame().next(10);
        frame.next(1);
        assertThat(frame.total()).isEqualTo(0);
    }

    @Test
    void normalFrame_strike() {
        Frame frame = new NormalFrame().next(10);
        frame.next(1).next(9);
        assertThat(frame.total()).isEqualTo(20);
        Frame frame2 = new NormalFrame().next(10);
        frame2.next(1).next(2);
        assertThat(frame2.total()).isEqualTo(13);
    }

    @Test
    void normalFrame9_spare_yet() {
        Frame frame = new NormalFrame(9).next(1).next(9);
        assertThat(frame.total()).isEqualTo(0);
    }

    @Test
    void normalFrame9_spare() {
        Frame frame = new NormalFrame(9).next(1).next(9);
        frame.next(1);
        assertThat(frame.total()).isEqualTo(11);
    }

    @Test
    void normalFrame9_strike_yet() {
        Frame frame = new NormalFrame(9).next(10);
        frame.next(10);
        assertThat(frame.total()).isEqualTo(0);
    }

    @Test
    void normalFrame9_strike() {
        Frame frame = new NormalFrame(9).next(10);
        frame.next(1).next(9);
        assertThat(frame.total()).isEqualTo(20);
    }

    @Test
    void FinalFrame_miss_yet() {
        Frame frame = new FinalFrame().next(1);
        assertThat(frame.total()).isEqualTo(0);
    }

    @Test
    void FinalFrame_miss() {
        Frame frame = new FinalFrame().next(1).next(2);
        assertThat(frame.total()).isEqualTo(3);
    }

    @Test
    void FinalFrame_spare_yet() {
        Frame frame = new FinalFrame().next(1).next(9);
        assertThat(frame.total()).isEqualTo(0);
    }

    @Test
    void FinalFrame_spare() {
        Frame frame = new FinalFrame().next(1).next(9);
        frame.next(10);
        assertThat(frame.total()).isEqualTo(20);
    }

    @Test
    void FinalFrame_strike_yet() {
        Frame frame = new FinalFrame().next(10);
        frame.next(10);
        assertThat(frame.total()).isEqualTo(0);
    }

    @Test
    void FinalFrame_strike() {
        Frame frame = new FinalFrame().next(10);
        frame.next(10).next(10);
        assertThat(frame.total()).isEqualTo(30);
    }
}
