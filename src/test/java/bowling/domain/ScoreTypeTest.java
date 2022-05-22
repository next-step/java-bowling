package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTypeTest {
    @Test
    void returnProperScoreType() {
        assertThat(ScoreType.of(10)).isEqualTo(ScoreType.STRIKE);
        assertThat(ScoreType.of(9)).isEqualTo(ScoreType.SECOND);
        assertThat(ScoreType.of(9, 1)).isEqualTo(ScoreType.SPARE);
        assertThat(ScoreType.of(8, 1)).isEqualTo(ScoreType.MISS);
        assertThat(ScoreType.of(0, 0)).isEqualTo(ScoreType.GUTTER);
    }

    @Test
    void ThrowError() {
        assertThatThrownBy(() -> ScoreType.of(9, 2)).isInstanceOf(RuntimeException.class);
    }
}
