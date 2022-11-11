package bowling.domain.status;

import bowling.domain.Pin;
import bowling.domain.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MissTest {

    @Test
    void addScore() {
        Miss miss = new Miss(new Pin(5), new Pin(3));
        Score score = miss.addScore(new Score(13, 1));
        assertThat(score).isEqualTo(new Score(18, 0));
    }

    @Test
    void addScore2() {
        Miss miss = new Miss(new Pin(5), new Pin(3));
        Score score = miss.addScore(new Score(13, 2));
        assertThat(score).isEqualTo(new Score(21, 0));
    }
}
