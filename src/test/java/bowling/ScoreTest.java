package bowling;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    void 스코어는_누적된다() {
        Score score = new Score();
        score.add(Pin.from(1));
        score.add(Pin.from(2));
        assertThat(score.getScore(1)).isEqualTo(3);
    }

    @Test
    void 스트라이크() {
        Score score = new Score();
        score.add(Pin.from(10));
        assertThat(score.isStrike()).isTrue();
    }

    @Test
    void 스페어() {
        Score score = new Score();
        score.add(Pin.from(1));
        score.add(Pin.from(9));
        assertThat(score.isSpare()).isTrue();
    }

    @Test
    void 미스() {
        Score score = new Score();
        score.add(Pin.from(1));
        score.add(Pin.from(3));
        assertThat(score.isMiss()).isTrue();
    }

    @Test
    void 거터() {
        Score score = new Score();
        score.add(Pin.from(0));
        assertThat(score.isGutter(1)).isTrue();
    }
}
