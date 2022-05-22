package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTypeTest {
    @Test
    void returnProperScoreType() {
        assertThat(ScoreType.of(10, Optional.empty())).isEqualTo(ScoreType.STRIKE);
        assertThat(ScoreType.of(9, Optional.empty())).isEqualTo(ScoreType.SECOND);
        assertThat(ScoreType.of(9, Optional.of(1))).isEqualTo(ScoreType.SPARE);
        assertThat(ScoreType.of(8, Optional.of(1))).isEqualTo(ScoreType.MISS);
        assertThat(ScoreType.of(0, Optional.of(0))).isEqualTo(ScoreType.GUTTER);
    }

    @Test
    void ThrowError() {
        assertThatThrownBy(() -> ScoreType.of(9, Optional.of(2))).isInstanceOf(RuntimeException.class);
    }
}
