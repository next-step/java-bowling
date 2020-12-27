package bowling.domain.bowl;

import bowling.domain.score.Pins;
import bowling.domain.state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created : 2020-12-24 오전 7:51
 * Developer : Seo
 */
class FinalBowlTest {
    private FinalBowl finalBowl;
    private State secondState;

    @BeforeEach
    void setUp() {
        secondState = new Strike();
    }

    @Test
    void init() {
        finalBowl = new FinalBowl(secondState);
        assertThat(finalBowl).isNotNull().isInstanceOf(FinalBowl.class);
    }

    @Test
    void strike() {
        finalBowl = new FinalBowl(secondState);
        State state = finalBowl.stroke(new Pins(10));
        assertThat(state).isInstanceOf(State.class).isInstanceOf(Strike.class);
    }

    @Test
    void spare() {
        secondState = new Miss(new Pins(10), new Pins(1));
        finalBowl = new FinalBowl(secondState);
        State state = finalBowl.stroke(new Pins(9));
        assertThat(state).isInstanceOf(State.class).isInstanceOf(Spare.class);
    }

    @Test
    void gutter() {
        finalBowl = new FinalBowl(secondState);
        State state = finalBowl.stroke(new Pins(0));
        assertThat(state).isInstanceOf(State.class).isInstanceOf(Gutter.class);
    }

    @Test
    void miss() {
        finalBowl = new FinalBowl(secondState);
        State state = finalBowl.stroke(new Pins(1));
        assertThat(state).isInstanceOf(State.class).isInstanceOf(Miss.class);
    }
}
