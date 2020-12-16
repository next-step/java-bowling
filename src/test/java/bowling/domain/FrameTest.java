package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created : 2020-12-16 오전 9:44
 * Developer : Seo
 */
class FrameTest {
    @Test
    void strike() {
        Score score = new Score(10);
        Frame frame = new Frame(1, score);
        assertThat(frame.isStrike()).isTrue();
    }

    @Test
    void spare() {
        Frame frame = new Frame(1, new Score(9));
        assertThat(frame.isStrike()).isFalse();
        assertThat(frame.isSpare(new Score(1))).isTrue();
    }

    @Test
    void next() {
        Frame normalFrame = new Frame(1, new Score(0));
        assertThat(normalFrame.nextFrame(new Score(10))).isNotNull().isInstanceOf(NormalFrame.class);

        Frame finalFrame = new Frame(9, new Score(0));
        assertThat(finalFrame.nextFrame(new Score(10))).isNotNull().isInstanceOf(FinalFrame.class);
    }

    @Test
    void name() {
        Frame.init();
    }
}
