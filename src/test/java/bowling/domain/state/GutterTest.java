package bowling.domain.state;

import bowling.domain.frame.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GutterTest {

    @Test
    void nextScore() {
        Gutter gutter = new Gutter();
        State stateTen = gutter.nextScore(Point.inputPoint(10));
        assertThat(stateTen.isSpare()).isTrue();

        State stateZero = gutter.nextScore(Point.inputPoint(0));
        assertThat(stateZero.getScore()).isEqualTo("-");
    }
}
