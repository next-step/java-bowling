package bowling.domain.state;

import bowling.domain.score.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created : 2020-12-24 오후 4:39
 * Developer : Seo
 */
class LastStateTest {

    private LastState lastState;

    @BeforeEach
    void setUp() {
        lastState = new LastState(new Strike(), new Strike());
    }

    @Test
    void init() {
        assertThat(lastState).isNotNull().isInstanceOf(LastState.class);
    }

    @Test
    void XX() {
        lastState.setSecondSymbol();
        assertThat(lastState.getSymbol()).isEqualTo("XX");
    }

    @Test
    void xNum() {
        lastState = new LastState(new Strike(), new Miss(new Pins(1)));
        lastState.setSecondSymbol();
        assertThat(lastState.getSymbol()).isEqualTo("X|1");
    }

    @Test
    void numX() {
        lastState = new LastState(new Miss(new Pins(1)), new Strike());
        lastState.setSecondSymbol();
        assertThat(lastState.getSymbol()).isEqualTo("1|X");
    }

    @Test
    void numGutter() {
        lastState = new LastState(new Miss(new Pins(1)), new Gutter(new Pins(0)));
        lastState.setSecondSymbol();
        assertThat(lastState.getSymbol()).isEqualTo("1|-");
    }

    @Test
    void gutterNum() {
        lastState = new LastState(new Gutter(new Pins(0)), new Miss(new Pins(1)));
        lastState.setSecondSymbol();
        assertThat(lastState.getSymbol()).isEqualTo("-|1");
    }

    @Test
    void numNum() {
        lastState = new LastState(new Miss(new Pins(1)), new Miss(new Pins(1)));
        lastState.setSecondSymbol();
        assertThat(lastState.getSymbol()).isEqualTo("1|1");
    }

    @Test
    void spare() {
        lastState = new LastState(new Miss(new Pins(1)), new Miss(new Pins(9)));
        lastState.setSecondSymbol();
        assertThat(lastState.getSymbol()).isEqualTo("1|/");
    }

    @Test
    void xxx() {
        lastState.setSecondSymbol();
        LastState thirdState = new LastState(lastState, new Strike());
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("XXX");
    }

    @Test
    void xxNumber() {
        lastState.setSecondSymbol();
        LastState thirdState = new LastState(lastState, new Miss(new Pins(1)));
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("XX|1");
    }

    @Test
    void xxGutter() {
        lastState.setSecondSymbol();
        LastState thirdState = new LastState(lastState, new Gutter(new Pins(0)));
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("XX-");
    }

    @Test
    void xNumX() {
        lastState = new LastState(new Strike(), new Miss(new Pins(1)));
        lastState.setSecondSymbol();
        LastState thirdState = new LastState(lastState, new Strike());
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("X|1|X");
    }

    @Test
    void numXX() {
        lastState = new LastState(new Miss(new Pins(1)), new Strike());
        lastState.setSecondSymbol();
        LastState thirdState = new LastState(lastState, new Strike());
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("1|XX");
    }

    @Test
    void xGutterX() {
        lastState = new LastState(new Strike(), new Gutter(new Pins(0)));
        lastState.setSecondSymbol();
        LastState thirdState = new LastState(lastState, new Strike());
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("X|-|X");
    }

    @Test
    void gutterXX() {
        lastState = new LastState(new Gutter(new Pins(0)), new Strike());
        lastState.setSecondSymbol();
        LastState thirdState = new LastState(lastState, new Strike());
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("-|XX");
    }
    @Test
    void gutterXNum() {
        lastState = new LastState(new Gutter(new Pins(0)), new Strike());
        lastState.setSecondSymbol();
        LastState thirdState = new LastState(lastState, new Miss(new Pins(1)));
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("-|X|1");
    }

    @Test
    void xGutterNum() {
        lastState = new LastState(new Strike(), new Gutter(new Pins(0)));
        lastState.setSecondSymbol();
        LastState thirdState = new LastState(lastState, new Miss(new Pins(1)));
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("X|-|1");
    }

    @Test
    void xGutterGutter() {
        lastState = new LastState(new Strike(), new Gutter(new Pins(0)));
        lastState.setSecondSymbol();
        LastState thirdState = new LastState(lastState, new Gutter(new Pins(0)));
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("X|-|-");
    }

    @Test
    void spareX() {
        lastState = new LastState(new Miss(new Pins(1)), new Miss(new Pins(9)));
        lastState.setSecondSymbol();
        LastState thirdState = new LastState(lastState, new Strike());
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("1|/|X");
    }

    @Test
    void spareGutter() {
        lastState = new LastState(new Miss(new Pins(1)), new Miss(new Pins(9)));
        lastState.setSecondSymbol();
        LastState thirdState = new LastState(lastState, new Gutter(new Pins(0)));
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("1|/|-");
    }

    @Test
    void spareNum() {
        lastState = new LastState(new Miss(new Pins(1)), new Miss(new Pins(9)));
        lastState.setSecondSymbol();
        LastState thirdState = new LastState(lastState, new Miss(new Pins(1)));
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("1|/|1");
    }

    @Test
    void getScore() {
        assertThat(lastState.getScore().getFrameScore()).isEqualTo(20);
    }

    @Test
    void isFinished() {
        lastState.setSecondSymbol();
        assertThat(lastState.isFinished()).isFalse();
        lastState.setThirdSymbol();
        assertThat(lastState.isFinished()).isTrue();
    }
}