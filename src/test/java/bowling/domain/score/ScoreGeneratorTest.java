package bowling.domain.score;

import bowling.domain.point.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class ScoreGeneratorTest {

    @Test
    void create() {
        Score2 score2 = ScoreGenerator.of(Point.of(3));
        assertThat(score2.getScore()).isEqualTo("3");
    }

    @Test
    void strike() {
        Score2 score2 = ScoreGenerator.of(Point.of(10));
        assertThat(score2.getScore()).isEqualTo("X");
    }

    @Test
    void spare() {
        Score2 firstRoll = ScoreGenerator.of(Point.of(5));
        Score2 secondRoll = firstRoll.nextScore(Point.of(5));
        assertThat(secondRoll.getScore()).isEqualTo("/");
    }

    @Test
    void gutter() {
        Score2 firstRoll = ScoreGenerator.of(Point.of(0));
        assertThat(firstRoll.getScore()).isEqualTo("-");
    }

    @Test
    void testPointOverflow() {
        assertThatThrownBy(() -> ScoreGenerator.of(Point.of(11)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
