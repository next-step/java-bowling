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
    private FinalFirstBowl finalFirstBowl;

    @BeforeEach
    void setUp() {
    }

    @Test
    void init() {
        finalFirstBowl = new FinalFirstBowl();
        assertThat(finalFirstBowl).isNotNull().isInstanceOf(FinalFirstBowl.class);
    }

    @Test
    void first() {
        finalFirstBowl = new FinalFirstBowl();
        State state = finalFirstBowl.stroke(new Pins(10));
        assertThat(state).isInstanceOf(State.class).isInstanceOf(FinalFirstState.class);
    }

    @Test
    void second() {
        finalFirstBowl = new FinalFirstBowl();
        State state = finalFirstBowl.stroke(new Pins(10));

        FinalSecondBowl finalSecondBowl = new FinalSecondBowl(state);
        State state2 = finalSecondBowl.stroke(new Pins(9));
        assertThat(state2).isInstanceOf(State.class).isInstanceOf(FinalSecondState.class);
    }

    @Test
    void third() {
        finalFirstBowl = new FinalFirstBowl();
        State state = finalFirstBowl.stroke(new Pins(10));

        FinalSecondBowl finalSecondBowl = new FinalSecondBowl(state);
        State state2 = finalSecondBowl.stroke(new Pins(9));

        FinalThirdBowl finalThirdBowl = new FinalThirdBowl(state, state2);
        State state3 = finalThirdBowl.stroke(new Pins(9));
        assertThat(state3).isInstanceOf(State.class).isInstanceOf(FinalThirdState.class);
    }
}
