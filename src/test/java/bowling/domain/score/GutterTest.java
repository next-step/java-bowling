package bowling.domain.score;

import bowling.domain.frame.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GutterTest {

    @Test
    void nextScore() {
        Gutter gutter = new Gutter();
        Score scoreTen = gutter.nextScore(Point.inputPoint(10));
        assertThat(scoreTen.isSpare()).isTrue();

        Score scoreZero = gutter.nextScore(Point.inputPoint(0));
        assertThat(scoreZero.getScore()).isEqualTo("-");
    }
}
