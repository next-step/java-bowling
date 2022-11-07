package bowling.domain.frame;

import bowling.domain.Pin;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    @Test
    void isFinished_false1() {
        Frame frame = FinalFrame.init();
        frame.bowl(new Pin(7));

        assertThat(frame.isFinished()).isFalse();
    }

    @Test
    void isFinished_false2() {
        Frame frame = FinalFrame.init();
        frame.bowl(new Pin(7));
        frame.bowl(new Pin(3));

        assertThat(frame.isFinished()).isFalse();
    }

    @Test
    void isFinished_false3() {
        Frame frame = FinalFrame.init();
        frame.bowl(new Pin(10));
        frame.bowl(new Pin(3));

        assertThat(frame.isFinished()).isFalse();
    }

    @Test
    void isFinished_true1() {
        Frame frame = FinalFrame.init();
        frame.bowl(new Pin(10));
        frame.bowl(new Pin(7));
        frame.bowl(new Pin(2));

        assertThat(frame.isFinished()).isTrue();
    }

    @Test
    void isFinished_true2() {
        Frame frame = FinalFrame.init();
        frame.bowl(new Pin(7));
        frame.bowl(new Pin(2));

        assertThat(frame.isFinished()).isTrue();
    }
}