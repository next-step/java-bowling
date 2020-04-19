package seul.bowling.domain.status;

import org.junit.jupiter.api.Test;
import seul.bowling.domain.Score;

import static org.assertj.core.api.Assertions.assertThat;

public class MissTest {
    @Test
    void judgmentStatus() {
        Status status = new Miss(new Score());

        Status newStatus = status.judgmentStatus(true);

        assertThat(status == newStatus).isTrue();
    }

    @Test
    void addScore() {
        Status status = new Miss(new Score());

        status.addScore(10);

        assertThat(status.getScore().getScore()).isEqualTo(10);
        assertThat(status.getScore().getBonusScoreCount()).isEqualTo(0);
    }
}
