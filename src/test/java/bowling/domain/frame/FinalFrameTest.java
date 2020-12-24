package bowling.domain.frame;

import bowling.domain.score.Pins;
import bowling.domain.state.Gutter;
import bowling.domain.state.LastState;
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
    private int userIndex;

    @BeforeEach
    void setUp() {
        userIndex = 0;
        frame = new FinalFrame();
        frame.bowl(new Pins(10));
    }

    @Test
    void init() {
        assertThat(frame).isNotNull().isInstanceOf(FinalFrame.class);
    }

    @Test
    void bowl_x() {
        assertThat(frame.getLastState()).isInstanceOf(Strike.class);
        assertThat(frame.getLastStateFrameScore()).isEqualTo(10);
        assertThat(frame.getLastStateFirstScore()).isEqualTo(10);
        assertThat(frame.getLastStateSecondScore()).isZero();
        assertThat(frame.getLastStateSymbol()).isEqualTo("X");
    }

    @Test
    void secondBowl_xx() {
        frame.secondBowl(userIndex, new Strike(), new Pins(10));
        assertThat(frame.getLastState()).isInstanceOf(LastState.class);
        assertThat(frame.getLastStateFrameScore()).isEqualTo(20);
        assertThat(frame.getLastStateSymbol()).isEqualTo("XX");
    }

    @Test
    void thirdBowl_xxx() {
        frame.thirdBowl(userIndex, new LastState(new Strike(), new Strike()), new Pins(10));
        assertThat(frame.getLastStateFrameScore()).isEqualTo(30);
        assertThat(frame.getLastStateSymbol()).isEqualTo("XXX");
    }
}
