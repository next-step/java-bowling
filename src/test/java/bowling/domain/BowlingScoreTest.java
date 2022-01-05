package bowling.domain;

import bowling.engine.Score;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class BowlingScoreTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10, 30})
    public void create(int score) {
        assertThat(BowlingScore.of(score)).isInstanceOf(BowlingScore.class);
        assertThat(BowlingScore.of(score)).isEqualTo(BowlingScore.of(score));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 31})
    public void createFailed(int score) {
        assertThatIllegalArgumentException().isThrownBy(() -> BowlingScore.of(score));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10, 30})
    public void score(int score) {
        assertThat(BowlingScore.of(score).toInt()).isEqualTo(score);
    }


    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10})
    public void add(int score) {
        final Score bowlingScore = BowlingScore.of(score);
        assertThat(bowlingScore.add(bowlingScore).toInt()).isEqualTo(score + score);
    }
}
