package bowling.domain.state;

import bowling.domain.point.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalTest {

    @Test
    void spare() {
        Normal normal = new Normal(Point.of(5));
        State state = normal.nextScore(Point.of(5));
        assertThat(state.getScore()).isEqualTo("/");
    }

    @Test
    void gutter() {
        Normal normal = new Normal(Point.of(3));
        State state = normal.nextScore(Point.of(0));
        assertThat(state.getScore()).isEqualTo("-");
    }

    @Test
    void miss() {
        Normal normal = new Normal(Point.of(3));
        State state = normal.nextScore(Point.of(4));
        assertThat(state.getScore()).isEqualTo("4");
    }
}
