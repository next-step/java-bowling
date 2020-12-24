package bowling.domain;

import bowling.domain.score.Pins;
import bowling.domain.score.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created : 2020-12-16 오전 8:21
 * Developer : Seo
 */
class ScoreTest {
    private Score score;

    @BeforeEach
    void setUp() {
        score = new Score(new Pins(10));
    }

    @Test
    void constructor() {
        assertThat(score).isNotNull().isInstanceOf(Score.class);
        assertThat(new Score()).isNotNull().isInstanceOf(Score.class);
    }

    @Test
    void first() {
        assertThat(score.getFirst().get()).isEqualTo(10);
        assertThat(score.getSecond().get()).isZero();
    }

    @Test
    void second() {
        score.setSecond(new Pins(10));
        assertThat(score.getFirst().get()).isEqualTo(10);
        assertThat(score.getSecond().get()).isEqualTo(10);
    }

    @Test
    void strike() {
        assertThat(score.isStrike()).isTrue();
    }

    @Test
    void spare() {
        score = new Score(new Pins(9));
        assertThat(score.isSpare(new Pins(1))).isTrue();
        assertThat(score.isSpare(new Pins(0))).isFalse();
    }

    @Test
    void gutter() {
        score = new Score(new Pins(0));
        assertThat(score.isGutter()).isTrue();
    }

    @Test
    void allGutter() {
        score = new Score(new Pins(0));
        assertThat(score.isAllGutter(new Pins(0))).isTrue();
    }

    @Test
    void getFrameScore() {
        score.setSecond(new Pins(0));
        assertThat(score.getFrameScore()).isEqualTo(10);
    }

    @Test
    void tostring() {
        assertThat(score.toString()).hasToString("10");

        score = new Score(new Pins(0));
        assertThat(score.toString()).hasToString("");
    }
}
