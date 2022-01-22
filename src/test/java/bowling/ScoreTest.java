package bowling;

import bowling.domain.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {

    @Test
    void convertStrike() {
        Score score = new Score(10);
        assertThat(score.convert()).isEqualTo("X");
    }

    @Test
    void convertZero() {
        Score score = new Score(0);
        assertThat(score.convert()).isEqualTo("-");
    }

    @Test
    void convert() {
        Score score = new Score(5);
        assertThat(score.convert()).isEqualTo("5");
    }

    @Test
    void add() {
        Score scoreA = new Score(5);
        Score scoreB = new Score(3);
        assertThat(scoreA.add(scoreB)).isEqualTo(new Score(8));
    }

    @Test
    void validationCheck() {
        assertThatThrownBy(() -> {
            Score score = new Score(11);
        }).hasMessageContaining("score must be between 0 and 10").isInstanceOf(IllegalArgumentException.class);
    }
}
