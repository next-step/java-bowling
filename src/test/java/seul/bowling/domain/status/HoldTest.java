package seul.bowling.domain.status;

import org.junit.jupiter.api.Test;
import seul.bowling.domain.Pins;
import seul.bowling.domain.Score;

import static org.assertj.core.api.Assertions.assertThat;

public class HoldTest {
    @Test
    void judgmentStatus() {
        Status status = new Hold(new Score(), new Pins());

        status = status.judgmentStatus(true);

        assertThat(status.getClass()).isEqualTo(Spare.class);
    }

    @Test
    void endJudgmentStatus() {
        Status status = new Hold(new Score(), new Pins());

        boolean endJudgementStatus = status.endJudgmentStatus();

        assertThat(endJudgementStatus).isFalse();
    }

    @Test
    void addScore() {
        Status status = new Hold(new Score(), new Pins());

        status.addScore(10);

        assertThat(status.getScore().getScore()).isEqualTo(10);
        assertThat(status.getScore().getBonusScoreCount()).isEqualTo(0);
    }
}
