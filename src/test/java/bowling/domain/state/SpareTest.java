package bowling.domain.state;

import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {

    @Test
    @DisplayName("다음 스코어 더하기")
    void addNextScore() {
        Pins pins = new Pins(0, Arrays.asList(1, 9));
        Spare spare = new Spare(pins);
        Score score = new Score();

        Score nextScore = spare.addNextScore(score);

        assertThat(nextScore).isEqualTo(new Score(10, 0));
    }
}