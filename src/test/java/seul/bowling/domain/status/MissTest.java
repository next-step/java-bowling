package seul.bowling.domain.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seul.bowling.domain.Pins;
import seul.bowling.domain.Score;

import static org.assertj.core.api.Assertions.assertThat;

public class MissTest {
    private Status status;

    @BeforeEach
    void setUp() {
        Score score = Score.of(3);
        Pins pins = Pins.of(1);
        pins.addPin(2, false);
        status = new Miss(pins, score);
    }

    @Test
    void addCumulativeScore() {
        status.addCumulativeScore(10);

        assertThat(status.getToTalScore()).isEqualTo(13);
    }

    @Test
    void getToTalScore() {
        assertThat(status.getToTalScore()).isEqualTo(3);
    }
}
