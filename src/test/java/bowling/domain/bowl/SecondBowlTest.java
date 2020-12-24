package bowling.domain.bowl;

import bowling.domain.score.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created : 2020-12-24 오전 7:47
 * Developer : Seo
 */
class SecondBowlTest {
    private SecondBowl secondBowl;

    @BeforeEach
    void setUp() {
        secondBowl = new SecondBowl(new Score(new Pins(1)));
    }

    @Test
    void constructor() {
        assertThat(secondBowl).isNotNull().isInstanceOf(SecondBowl.class);
    }

    @Test
    void spare() {
        State state = secondBowl.stroke(new Pins(9));
        assertThat(state).isInstanceOf(State.class).isInstanceOf(Spare.class);
    }

    @Test
    void allGutter() {
        secondBowl = new SecondBowl(new Score(new Pins(0)));
        State state = secondBowl.stroke(new Pins(0));
        assertThat(state).isInstanceOf(State.class).isInstanceOf(Gutter.class);
    }

    @Test
    void miss() {
        State state = secondBowl.stroke(new Pins(1));
        assertThat(state).isInstanceOf(State.class).isInstanceOf(Miss.class);
    }
}