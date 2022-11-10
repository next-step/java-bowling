package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.Score;
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
        frame.bowl(new Pin(3));
        frame.bowl(new Pin(9));

        assertThat(frame.isFinished()).isTrue();
    }

    @Test
    void isFinished_true3() {
        Frame frame = FinalFrame.init();
        frame.bowl(new Pin(10));
        frame.bowl(new Pin(9));
        frame.bowl(new Pin(1));
        assertThat(frame.isFinished()).isTrue();
    }

    @Test
    void isFinished_true4() {
        FinalFrame frame = FinalFrame.init();
        frame.bowl(new Pin(10));
        frame.bowl(new Pin(10));
        frame.bowl(new Pin(10));
        assertThat(frame.isFinished()).isTrue();
    }

    @Test
    void getScore_firstBowl() {
        Frame frame = FinalFrame.init();
        frame.bowl(new Pin(7));

        assertThat(frame.getScore()).isEqualTo(new Score(7, 0));
    }

    @Test
    void getScore_strike() {
        Frame frame = FinalFrame.init();
        frame.bowl(new Pin(10));
        frame.bowl(new Pin(9));

        assertThat(frame.getScore().getScore()).isEqualTo(19);
    }
}
