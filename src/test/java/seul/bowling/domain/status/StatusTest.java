package seul.bowling.domain.status;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StatusTest {
    @Test
    void addScore() {
        Status status = new Status();

        status.addScore(10);

        assertThat(status.getScore().getScore()).isEqualTo(10);
        assertThat(status.getScore().getBonusScoreCount()).isEqualTo(0);
    }

    @Test
    void judgmentStatus_strike() {
        Status status = new Status();

        status = status.judgmentStatus(true);

        assertThat(status.getClass()).isEqualTo(Strike.class);
    }

    @Test
    void addCumulativeScore() {
        Status status = new Status();

        status.addCumulativeScore(10);

        assertThat(status.getScore().getScore()).isEqualTo(10);
    }

    @Test
    void addBonusScore() {
        Status status = new Status();

        status.addBonusScore(10);

        assertThat(status.getScore().getScore()).isEqualTo(10);
    }

    @Test
    void endScore() {
        Status status = new Status();

        boolean endScore = status.endScore();

        assertThat(endScore).isFalse();
    }

    @Test
    void availableAddBonusScore() {
        Status status = new Status();

        boolean availableAddBonusScore = status.availableAddBonusScore();

        assertThat(availableAddBonusScore).isFalse();
    }

    @Test
    void getToTalScore() {
        Status status = new Status();
        status.addScore(10);
        status.addBonusScore(10);

        assertThat(status.getToTalScore()).isEqualTo(20);
    }
}