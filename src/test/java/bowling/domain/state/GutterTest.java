package bowling.domain.state;

import bowling.domain.point.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GutterTest {

    @Test
    void gutter() {
        Gutter gutter = new Gutter();
        State state = gutter.nextScore(Point.of(0));
        assertThat(state.getScore()).isEqualTo("-");
    }

    @Test
    void nextScore() {
        Gutter gutter = new Gutter();
        gutter.nextScore(Point.of(5));

        State state = gutter.nextScore(Point.of(0));
        assertThat(state.getScore()).isEqualTo("-");
    }



}
