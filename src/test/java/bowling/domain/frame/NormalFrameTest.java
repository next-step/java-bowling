package bowling.domain.frame;

import bowling.domain.score.Pins;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created : 2020-12-24 오전 9:57
 * Developer : Seo
 */
class NormalFrameTest {
    private Frame frame;

    @BeforeEach
    void setUp() {
        frame = new NormalFrame();
    }

    @Test
    void init() {
        assertThat(frame).isNotNull().isInstanceOf(NormalFrame.class);
    }

    @Test
    void stroke() {
        frame.stroke(0, new Pins(10));
        assertThat(frame.getState()).isInstanceOf(Strike.class);
    }

    @Test
    void spare() {
        frame.stroke(0, new Pins(7));
        frame.spare(0, new Pins(3));
        assertThat(frame.getState()).isInstanceOf(Spare.class);
    }

    @Test
    void getState() {
        frame.stroke(0, new Pins(10));
        assertThat(frame.getState()).isInstanceOf(Strike.class);
    }

    @Test
    void getSymbol() {
        frame.stroke(0, new Pins(10));
        assertThat(frame.getSymbol()).isEqualTo("X");
    }

    @Test
    void getFrameScore() {
        frame.stroke(0, new Pins(10));
        assertThat(frame.getFrameScore()).isEqualTo(10);
    }

    @Test
    void getFirstScore() {
        frame.stroke(0, new Pins(10));
        assertThat(frame.getFirstScore()).isEqualTo(10);
    }

    @Test
    void getSecondScore() {
        frame.stroke(0, new Pins(7));
        frame.spare(0, new Pins(3));
        assertThat(frame.getSecondScore()).isEqualTo(3);
    }

    @Test
    void symbol_strike() {
        frame.stroke(0, new Pins(10));
        assertThat(frame.getSymbol()).isEqualTo("X");
    }

    @Test
    void symbol_spare() {
        frame.stroke(0, new Pins(9));
        frame.spare(0, new Pins(1));
        assertThat(frame.getSymbol()).isEqualTo("9|/");
    }

    @Test
    void symbol_miss() {
        frame.stroke(0, new Pins(8));
        frame.spare(0, new Pins(1));
        assertThat(frame.getSymbol()).isEqualTo("8|1");
    }

    @Test
    void symbol_gutter() {
        frame.stroke(0, new Pins(0));
        frame.spare(0, new Pins(1));
        assertThat(frame.getSymbol()).isEqualTo("-|1");
    }

    @Test
    void symbol_gutter2() {
        frame.stroke(0, new Pins(1));
        frame.spare(0, new Pins(0));
        assertThat(frame.getSymbol()).isEqualTo("1|-");
    }

    @Test
    void symbol_gutter3() {
        frame.stroke(0, new Pins(0));
        frame.spare(0, new Pins(0));
        assertThat(frame.getSymbol()).isEqualTo("-|-");
    }
}
