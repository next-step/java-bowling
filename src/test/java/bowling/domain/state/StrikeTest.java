package bowling.domain.state;

import bowling.domain.Score;
import bowling.exception.BowlingGameException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StrikeTest {
    @Test
    void 스트라이크상태에서_bowl() {
        assertThatThrownBy(() -> {
            new Strike().bowl(1);
        }).isInstanceOf(BowlingGameException.class);
    }

    @Test
    void 스트라이크_남은횟수_없을때_추가점수_계산() {
        assertThat(new Strike()
                .calculateScore(new Score(10,0))).isEqualTo(new Score(10,0));
    }

    @Test
    void 스트라이크_남은횟수_있을때_추가점수_계산() {
        assertThat(new Strike()
                .calculateScore(new Score(10,1))).isEqualTo(new Score(20,0));
    }
}