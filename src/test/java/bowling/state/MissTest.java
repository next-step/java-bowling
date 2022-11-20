package bowling.state;

import static org.assertj.core.api.Assertions.*;

import bowling.Pin;
import bowling.Score;
import org.junit.jupiter.api.Test;

class MissTest {

    @Test
    void 점수계산_가능() {
        Miss miss = new Miss(Pin.from(1), Pin.from(2));
        Score score = miss.score();
        assertThat(score.getScore()).isEqualTo(3);
    }

    @Test
    void 이전_스트라이크와_점수합산() {
        Score beforeScore = Score.ofStrike();
        Miss miss = new Miss(Pin.from(1), Pin.from(2));
        Score score = miss.calculateAdditionalScore(beforeScore);
        assertThat(score.getScore()).isEqualTo(13);
    }
}
