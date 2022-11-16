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
        Score score = new Score(1);
        score.add(new Score(3));
        assertThat(score).isEqualTo(new Score(4));
    }

    @Test
    void 보너스_더하기() {
        Score score = new Score(10, 1);
        score.addBonus(new Pin(1));
        assertThat(score).isEqualTo(new Score(11, 0));
    }

    @Test
    void 완료된_스코어_구하기() {
        assertThat(Score.end(10)).isEqualTo(new Score(10, 0));
    }

    @Test
    void 빈_스코어_생성() {
        assertThat(Score.zero()).isEqualTo(new Score(0, 0));
    }
}
