package bowling.domain.frame;

import bowling.domain.score.Pins;
import bowling.domain.state.FinalFirstState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created : 2020-12-16 오전 8:59
 * Developer : Seo
 */
class FinalFrameTest {
    private Frame frame;

    @BeforeEach
    void setUp() {
        frame = new FinalFrame();
    }

    @Test
    void init() {
        assertThat(frame).isNotNull().isInstanceOf(FinalFrame.class);
    }

    @Test
    void stroke() {
        frame.stroke(0, new Pins(10));
        assertThat(frame.getState()).isInstanceOf(FinalFirstState.class);
    }

    @Test
    void stroke_isFinished() {
        frame.stroke(0, new Pins(10));
        assertThat(frame.getState().isFinished()).isFalse();
    }

    @Test
    void spare_isFinished() {
        frame.stroke(0, new Pins(10));
        frame.spare(0, new Pins(10));
        assertThat(frame.getState().isFinished()).isFalse();
    }

    @Test
    void spare_finished() {
        frame.stroke(0, new Pins(10));
        frame.spare(0, new Pins(10));
        assertThat(frame.getState().isFinished()).isFalse();
        frame.spare(0, new Pins(10));
        assertThat(frame.getState().isFinished()).isTrue();
    }
}
