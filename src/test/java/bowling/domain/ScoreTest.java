package bowling.domain;

import bowling.domain.score.Pins;
import bowling.domain.score.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    }

    @Test
    void strike() {
        assertThat(score.isStrike()).isTrue();
    }

    @Test
    void gutter() {
        score = new Score(new Pins(0));
        assertThat(score.isGutter()).isTrue();

        score = new Score(new Pins(1));
        score.setSecond(new Pins(0));
        assertThat(score.isGutter()).isTrue();
    }

    @Test
    void getFirst() {
        assertThat(score.getFirst().get()).isEqualTo(10);
    }

    @Test
    void getSecond() {
        score = new Score(new Pins(1));
        score.setSecond(new Pins(1));
        assertThat(score.getSecond().get()).isEqualTo(1);
    }

    @DisplayName("첫 구가 스트라이크")
    @Test
    void firstStrike() {
        assertThatThrownBy(() -> score.setSecond(new Pins(1)))
                .withFailMessage("첫 구가 스트라이트")
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void spare() {
        score = new Score(new Pins(9));
        score.setSecond(new Pins(1));
        assertThat(score.isSpare()).isTrue();
    }

    @Test
    void first() {
        score = new Score(new Pins(9));
        assertThat(score.isFirst()).isTrue();

        score.setSecond(new Pins(1));
        assertThat(score.isFirst()).isFalse();
    }
}
