package bowling.domain.score;

import bowling.domain.point.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GutterTest {

    @Test
    void gutter() {
        Gutter gutter = new Gutter();
        Score2 score2 = gutter.nextScore(Point.of(0));
        assertThat(score2.getScore()).isEqualTo("-");
    }

    @Test
    void nextScore() {
        Gutter gutter = new Gutter();
        gutter.nextScore(Point.of(5));

        Score2 score2 = gutter.nextScore(Point.of(0));
        assertThat(score2.getScore()).isEqualTo("-");
    }



}
