package bowling.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ScoreTest {
    @Test
    public void create() {
        assertThatCode(() -> new Score(10, 1)).doesNotThrowAnyException();
    }

    @Test
    public void invalid_lessThan_minScore() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Score(-1, 0);
        });
    }

    @Test
    public void invalid_overThan_maxScore() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Score(31, 0);
        });
    }

    @Test
    public void invalid_lessThan_minLeft() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Score(0, -1);
        });
    }

    @Test
    public void invalid_overThan_maxLeft() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Score(0, 4);
        });
    }

    @Test
    public void calculable_true() {
        final Score score = new Score(10, 0);

        assertThat(score.calculable()).isTrue();
    }

    @Test
    public void calculable_false() {
        final Score score = new Score(10, 1);

        assertThat(score.calculable()).isFalse();
    }

    @Test
    public void score() {
        Score score = new Score(10, 2);
        score = score.play(10);
        score = score.play(10);

        assertThat(score.score()).isEqualTo(30);
    }

    @Test
    public void score_invalid() {
        assertThatIllegalStateException().isThrownBy(() -> {
            Score score = new Score(10, 2);
            score = score.play(10);

            score.score();
        });
    }
}
