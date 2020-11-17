package bowling.domain.state;

import bowling.domain.point.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class StateGeneratorTest {

    @Test
    void create() {
        State state = StateGenerator.of(Point.of(3));
        assertThat(state.getScore()).isEqualTo("3");
    }

    @Test
    void strike() {
        State state = StateGenerator.of(Point.of(10));
        assertThat(state.getScore()).isEqualTo("X");
    }

    @Test
    void spare() {
        State firstRoll = StateGenerator.of(Point.of(5));
        State secondRoll = firstRoll.nextScore(Point.of(5));
        assertThat(secondRoll.getScore()).isEqualTo("/");
    }

    @Test
    void gutter() {
        State firstRoll = StateGenerator.of(Point.of(0));
        assertThat(firstRoll.getScore()).isEqualTo("-");
    }

    @Test
    void testPointOverflow() {
        assertThatThrownBy(() -> StateGenerator.of(Point.of(11)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
