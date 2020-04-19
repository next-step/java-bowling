package seul.bowling.domain.status;

import org.junit.jupiter.api.Test;
import seul.bowling.domain.Pins;
import seul.bowling.domain.Score;

import static org.assertj.core.api.Assertions.assertThat;

public class StrikeTest {
    @Test
    void addScore() {
        Status status = new Strike(new Score(), new Pins());

        status.addScore(10);

        assertThat(status.getScore().getScore()).isEqualTo(10);
        assertThat(status.getScore().getBonusScoreCount()).isEqualTo(2);
    }

    @Test
    void endScore() {
        Status status = new Strike(new Score(), new Pins());
        status.addScore(10);

        boolean endScore = status.endScore();

        assertThat(endScore).isFalse();
        assertThat(status.getScore().getBonusScoreCount()).isEqualTo(2);
    }
}
