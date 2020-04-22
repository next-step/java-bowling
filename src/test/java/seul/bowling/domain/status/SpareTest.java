package seul.bowling.domain.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seul.bowling.domain.Pins;
import seul.bowling.domain.Score;

import static org.assertj.core.api.Assertions.assertThat;

public class SpareTest {
    private Status status;

    @BeforeEach
    void setUp() {
        Score score = Score.of(10);
        Pins pins = Pins.of(1);
        pins.addPin(9, false);

        this.status = new Spare(pins, score);
    }

    @Test
    void addPins() {
        status = status.addPins(10);

        assertThat(status.getClass()).isEqualTo(Spare.class);
    }

    @Test
    void addBonusScore() {
        status.addBonusScore(10);

        assertThat(status.getToTalScore()).isEqualTo(20);
        assertThat(status.end()).isTrue();
    }

    @Test
    void addCumulativeScore() {
        status.addCumulativeScore(10);

        assertThat(status.getToTalScore()).isEqualTo(20);
    }

    @Test
    void getToTalScore() {
        int totalScore = status.getToTalScore();

        assertThat(totalScore).isEqualTo(10);
    }

    @Test
    void endCalculateScore() {
        boolean end = status.endCalculateScore();

        assertThat(end).isFalse();
    }
}
