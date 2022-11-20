package bowling.state;

import static org.assertj.core.api.Assertions.*;

import bowling.Pin;
import bowling.Score;
import org.junit.jupiter.api.Test;

class SpareTest {

    @Test
    void 점수계산_불가() {
        Spare spare = new Spare(Pin.from(9), Pin.from(1));
        Score score = spare.score();
        assertThatThrownBy(() -> score.getScore())
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 이전_스트라이크와_점수합산() {
        Score beforeScore = Score.ofStrike();
        Spare spare = new Spare(Pin.from(9), Pin.from(1));
        Score score = spare.calculateAdditionalScore(beforeScore);
        assertThat(score.getScore()).isEqualTo(20);
    }
}
