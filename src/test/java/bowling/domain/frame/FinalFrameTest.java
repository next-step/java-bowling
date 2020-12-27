package bowling.domain.frame;

import bowling.domain.score.Pins;
import bowling.domain.state.FinalState;
import bowling.domain.state.State;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created : 2020-12-16 오전 8:59
 * Developer : Seo
 */
class FinalFrameTest {
    private Frame frame;
    private int playerIndex;

    @BeforeEach
    void setUp() {
        playerIndex = 0;
        frame = new FinalFrame();
    }

    @Test
    void init() {
        assertThat(frame).isNotNull().isInstanceOf(FinalFrame.class);
    }

    @Test
    void next_final() {
        assertThat(frame.next()).isNull();
    }

    @Test
    void stroke() {
        frame.stroke(playerIndex, new Pins(10));
        assertThat(frame.getLastState()).isInstanceOf(FinalState.class);
        assertThat(frame.getState(playerIndex)).isInstanceOf(FinalState.class);
    }

    @Test
    void stroke_isFinished() {
        State state = frame.stroke(playerIndex, new Pins(10));
        assertThat(state.isFinished()).isFalse();
    }

    @Test
    void spare_isFinished() {
        frame.stroke(playerIndex, new Pins(10));
        State state = frame.spare(playerIndex, new Pins(10));
        assertThat(state.isFinished()).isFalse();
    }

    @Test
    void spare_finished() {
        frame.stroke(playerIndex, new Pins(10));
        State second = frame.spare(playerIndex, new Pins(10));
        assertThat(second.isFinished()).isFalse();
        State state = frame.spare(playerIndex, new Pins(10));
        assertThat(state.isFinished()).isTrue();
    }
}
