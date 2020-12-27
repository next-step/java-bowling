package bowling.domain.state;

import bowling.domain.score.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created : 2020-12-24 오후 4:39
 * Developer : Seo
 */
class FinalStateTest {

    private FinalState finalState;

    @BeforeEach
    void setUp() {
        finalState = new FinalState(new Strike(), new Strike());
    }

    @Test
    void init() {
        assertThat(finalState).isNotNull().isInstanceOf(FinalState.class);
        assertThat(finalState).isNotNull().isInstanceOf(State.class);
    }

    @Test
    void XX() {
        finalState.setSecondSymbol();
        assertThat(finalState.getSymbol()).isEqualTo("XX");
    }

    @Test
    void xNum() {
        finalState = new FinalState(new Strike(), new Miss(new Pins(1)));
        finalState.setSecondSymbol();
        assertThat(finalState.getSymbol()).isEqualTo("X|1");
    }

    @Test
    void numX() {
        finalState = new FinalState(new Miss(new Pins(1)), new Strike());
        finalState.setSecondSymbol();
        assertThat(finalState.getSymbol()).isEqualTo("1|X");
    }

    @Test
    void numGutter() {
        finalState = new FinalState(new Miss(new Pins(1)), new Gutter(new Pins(0)));
        finalState.setSecondSymbol();
        assertThat(finalState.getSymbol()).isEqualTo("1|-");
    }

    @Test
    void gutterNum() {
        finalState = new FinalState(new Gutter(new Pins(0)), new Miss(new Pins(1)));
        finalState.setSecondSymbol();
        assertThat(finalState.getSymbol()).isEqualTo("-|1");
    }

    @Test
    void numNum() {
        finalState = new FinalState(new Miss(new Pins(1)), new Miss(new Pins(1)));
        finalState.setSecondSymbol();
        assertThat(finalState.getSymbol()).isEqualTo("1|1");
    }

    @Test
    void spare() {
        finalState = new FinalState(new Miss(new Pins(1)), new Miss(new Pins(9)));
        finalState.setSecondSymbol();
        assertThat(finalState.getSymbol()).isEqualTo("1|/");
    }

    @Test
    void xxx() {
        finalState.setSecondSymbol();
        FinalState thirdState = new FinalState(finalState, new Strike());
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("XXX");
    }

    @Test
    void xxNumber() {
        finalState.setSecondSymbol();
        FinalState thirdState = new FinalState(finalState, new Miss(new Pins(1)));
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("XX|1");
    }

    @Test
    void xxGutter() {
        finalState.setSecondSymbol();
        FinalState thirdState = new FinalState(finalState, new Gutter(new Pins(0)));
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("XX-");
    }

    @Test
    void xNumX() {
        finalState = new FinalState(new Strike(), new Miss(new Pins(1)));
        finalState.setSecondSymbol();
        FinalState thirdState = new FinalState(finalState, new Strike());
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("X|1|X");
    }

    @Test
    void numXX() {
        finalState = new FinalState(new Miss(new Pins(1)), new Strike());
        finalState.setSecondSymbol();
        FinalState thirdState = new FinalState(finalState, new Strike());
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("1|XX");
    }

    @Test
    void xGutterX() {
        finalState = new FinalState(new Strike(), new Gutter(new Pins(0)));
        finalState.setSecondSymbol();
        FinalState thirdState = new FinalState(finalState, new Strike());
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("X|-|X");
    }

    @Test
    void gutterXX() {
        finalState = new FinalState(new Gutter(new Pins(0)), new Strike());
        finalState.setSecondSymbol();
        FinalState thirdState = new FinalState(finalState, new Strike());
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("-|XX");
    }
    @Test
    void gutterXNum() {
        finalState = new FinalState(new Gutter(new Pins(0)), new Strike());
        finalState.setSecondSymbol();
        FinalState thirdState = new FinalState(finalState, new Miss(new Pins(1)));
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("-|X|1");
    }

    @Test
    void xGutterNum() {
        finalState = new FinalState(new Strike(), new Gutter(new Pins(0)));
        finalState.setSecondSymbol();
        FinalState thirdState = new FinalState(finalState, new Miss(new Pins(1)));
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("X|-|1");
    }

    @Test
    void xGutterGutter() {
        finalState = new FinalState(new Strike(), new Gutter(new Pins(0)));
        finalState.setSecondSymbol();
        FinalState thirdState = new FinalState(finalState, new Gutter(new Pins(0)));
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("X|-|-");
    }

    @Test
    void spareX() {
        finalState = new FinalState(new Miss(new Pins(1)), new Miss(new Pins(9)));
        finalState.setSecondSymbol();
        FinalState thirdState = new FinalState(finalState, new Strike());
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("1|/|X");
    }

    @Test
    void spareGutter() {
        finalState = new FinalState(new Miss(new Pins(1)), new Miss(new Pins(9)));
        finalState.setSecondSymbol();
        FinalState thirdState = new FinalState(finalState, new Gutter(new Pins(0)));
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("1|/|-");
    }

    @Test
    void spareNum() {
        finalState = new FinalState(new Miss(new Pins(1)), new Miss(new Pins(9)));
        finalState.setSecondSymbol();
        FinalState thirdState = new FinalState(finalState, new Miss(new Pins(1)));
        thirdState.setThirdSymbol();
        assertThat(thirdState.getSymbol()).isEqualTo("1|/|1");
    }

    @Test
    void getScore() {
        assertThat(finalState.getScore().getFrameScore()).isEqualTo(20);
    }
}
