package bowling.state;

import static org.assertj.core.api.Assertions.*;

import bowling.CannotCalculateException;
import bowling.Score;
import org.junit.jupiter.api.Test;

class StrikeTest {

    @Test
    void 점수계산_불가() {
        Strike strike = new Strike();
        Score score = strike.score();
        assertThatThrownBy(() -> score.getScore())
            .isInstanceOf(CannotCalculateException.class);
    }

    @Test
    void 이전_스페어와_점수합산() {
        Score beforeScore = Score.ofSpare();
        Strike strike = new Strike();
        Score score = strike.calculateAdditionalScore(beforeScore);
        assertThat(score.getScore()).isEqualTo(20);
    }
}
