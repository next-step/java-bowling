package bowling;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class ScoreTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void 스코어_0미만_또는_10초과(int count) {
        assertThatThrownBy(() -> new Score(count))
                .isInstanceOf(BowlingGameException.class)
                .hasMessage(ErrorMessage.SCORE_OUT_OF_RANGE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 10})
    void 스코어_0이상_10이하(int count) {
        assertThatNoException().isThrownBy(() -> new Score(count));
    }

    @Test
    void 스코어_더하기() {
        assertThat(new Score(1).add(new Score(2))).isEqualTo(new Score(3));
    }
}
