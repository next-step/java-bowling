package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTypeTest {
    @Test
    @DisplayName("10:STRIKE, 9:SECOND, 9|1:SPARE, 8|1:MISS, 0|0:GUTTER")
    void returnProperScoreType() {
        assertThat(ScoreType.of(10)).isEqualTo(ScoreType.STRIKE);
        assertThat(ScoreType.of(9)).isEqualTo(ScoreType.SECOND);
        assertThat(ScoreType.of(9, 1)).isEqualTo(ScoreType.SPARE);
        assertThat(ScoreType.of(8, 1)).isEqualTo(ScoreType.MISS);
        assertThat(ScoreType.of(0, 0)).isEqualTo(ScoreType.GUTTER);
    }

    @Test
    @DisplayName("first와 second 합이 10을 넘으면 에러")
    void ThrowError() {
        assertThatThrownBy(() -> ScoreType.of(9, 2)).isInstanceOf(RuntimeException.class);
    }
}
