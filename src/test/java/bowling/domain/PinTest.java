package bowling.domain;

import bowling.exception.BowlingGameException;
import bowling.exception.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class PinTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void 스코어_0미만_또는_10초과(int count) {
        assertThatThrownBy(() -> new Pin(count))
                .isInstanceOf(BowlingGameException.class)
                .hasMessage(ErrorMessage.SCORE_OUT_OF_RANGE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 10})
    void 스코어_0이상_10이하(int count) {
        assertThatNoException().isThrownBy(() -> new Pin(count));
    }

    @Test
    void 스코어_더하기() {
        assertThat(new Pin(1).add(new Pin(2))).isEqualTo(new Pin(3));
    }
}
