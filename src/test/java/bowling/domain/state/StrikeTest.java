package bowling.domain.state;

import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {

    @Test
    @DisplayName("다음 스코어 더하기")
    void addNextScore() {
        Pins pins = new Pins(0, Collections.singletonList(10));
        Strike strike = new Strike(pins);
        Score score = new Score();

        Score nextScore = strike.addNextScore(score);

        assertThat(nextScore).isEqualTo(new Score(10, 1));
    }
}