package bowling.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FrameScoreTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10, 30})
    public void create(int score) {
        assertThat(FrameScore.of(score)).isInstanceOf(FrameScore.class);
        assertThat(FrameScore.of(score)).isEqualTo(FrameScore.of(score));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 31})
    public void createFailed(int score) {
        assertThatIllegalArgumentException().isThrownBy(() -> FrameScore.of(score));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10, 30})
    public void score(int score) {
        assertThat(FrameScore.of(score).toInt()).isEqualTo(score);
    }
}
