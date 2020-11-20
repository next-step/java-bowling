package bowling.domain.score;

import bowling.domain.point.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalTest {

    @Test
    void spare() {
        Normal normal = new Normal(Point.of(5));
        Score2 score2 = normal.nextScore(Point.of(5));
        assertThat(score2.getScore()).isEqualTo("/");
    }

    @Test
    void gutter() {
        Normal normal = new Normal(Point.of(3));
        Score2 score2 = normal.nextScore(Point.of(0));
        assertThat(score2.getScore()).isEqualTo("-");
    }

    @Test
    void miss() {
        Normal normal = new Normal(Point.of(3));
        Score2 score2 = normal.nextScore(Point.of(4));
        assertThat(score2.getScore()).isEqualTo("4");
    }
}
