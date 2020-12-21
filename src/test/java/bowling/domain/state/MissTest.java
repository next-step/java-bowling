package bowling.domain.state;

import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class MissTest {

    @Test
    @DisplayName("다음 스코어 더하기")
    void addNextScore() {
        Pins pins = new Pins(1, Arrays.asList(1, 7));
        Miss miss = new Miss(pins, 8);
        Score score = new Score();

        Score nextScore = miss.addNextScore(score);

        assertThat(nextScore).isEqualTo(new Score(8, 0));
    }
}