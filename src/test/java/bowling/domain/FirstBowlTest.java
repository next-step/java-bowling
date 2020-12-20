package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FirstBowlTest {
    private FirstBowl firstBowl;

    @BeforeEach
    void setUp() {
        firstBowl = new FirstBowl();
    }

    @Test
    void constructor() {
        assertThat(firstBowl).isNotNull().isInstanceOf(FirstBowl.class);
    }

    @Test
    void strike() {
        State state = firstBowl.stroke(new Pins(10));
        assertThat(state).isInstanceOf(State.class).isInstanceOf(Strike.class);
    }

    @Test
    void gutter() {
        State state = firstBowl.stroke(new Pins(0));
        assertThat(state).isInstanceOf(State.class).isInstanceOf(Gutter.class);
    }

    @Test
    void miss() {
        State state = firstBowl.stroke(new Pins(1));
        assertThat(state).isInstanceOf(State.class).isInstanceOf(Miss.class);
    }
}
