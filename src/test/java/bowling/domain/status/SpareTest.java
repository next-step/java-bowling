package bowling.domain.status;

import bowling.domain.Pin;
import bowling.domain.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpareTest {

    @Test
    void addScore() {
        Spare spare = new Spare(new Pin(5), new Pin(5));
        Score score = spare.addScore(new Score(13, 1));
        assertThat(score).isEqualTo(new Score(18, 0));
    }

    @Test
    void addScore2() {
        Spare spare = new Spare(new Pin(5), new Pin(5));
        Score score = spare.addScore(new Score(13, 2));
        assertThat(score).isEqualTo(new Score(23, 0));
    }
}
