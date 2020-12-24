package bowling.domain.bowl;

import bowling.domain.score.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created : 2020-12-24 오전 7:51
 * Developer : Seo
 */
class ThirdBowlTest {
    private ThirdBowl thirdBowl;
    private State secondState;

    @BeforeEach
    void setUp() {
        secondState = new Strike();
    }

    @Test
    void constructor() {
        thirdBowl = new ThirdBowl(secondState);
        assertThat(thirdBowl).isNotNull().isInstanceOf(ThirdBowl.class);
    }

    @Test
    void strike() {
        thirdBowl = new ThirdBowl(secondState);
        State state = thirdBowl.stroke(new Pins(10));
        assertThat(state).isInstanceOf(State.class).isInstanceOf(Strike.class);
    }

    @Test
    void spare() {
        secondState = new Miss(new Pins(10), new Pins(1));
        thirdBowl = new ThirdBowl(secondState);
        State state = thirdBowl.stroke(new Pins(9));
        assertThat(state).isInstanceOf(State.class).isInstanceOf(Spare.class);
    }

    @Test
    void gutter() {
        thirdBowl = new ThirdBowl(secondState);
        State state = thirdBowl.stroke(new Pins(0));
        assertThat(state).isInstanceOf(State.class).isInstanceOf(Gutter.class);
    }

    @Test
    void miss() {
        thirdBowl = new ThirdBowl(secondState);
        State state = thirdBowl.stroke(new Pins(1));
        assertThat(state).isInstanceOf(State.class).isInstanceOf(Miss.class);
    }
}
