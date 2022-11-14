package bowling.domain;

import bowling.exception.BowlingGameException;
import bowling.exception.ErrorMessage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ScoreTest {
    @Test
    void 스코어가_0미만() {
        assertThatThrownBy(() -> new Score(-1))
                .isInstanceOf(BowlingGameException.class)
                .hasMessage(ErrorMessage.SCORE_OUT_OF_RANGE.getMessage());
    }

    @Test
    void 스코어_0이상() {
        assertThatNoException().isThrownBy(() -> new Score(0));
    }

    @Test
    void 스코어_더하기() {
        assertThat(new Score(1).add(new Score(3))).isEqualTo(new Score(4));
    }
}
