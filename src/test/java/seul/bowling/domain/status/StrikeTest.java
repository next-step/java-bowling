package seul.bowling.domain.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StrikeTest {
    private Status status;

    @BeforeEach
    void setUp() {
        status = new Strike(10);
    }

    @Test
    void addPins() {
        status.addPins(10);

        assertThat(status.getClass()).isEqualTo(Strike.class);
    }

    @Test
    void addBonusScore() {
        this.status.addBonusScore(10);

        assertThat(status.getToTalScore()).isEqualTo(20);
    }

    @Test
    void end() {
        boolean end = this.status.end();

        assertThat(end).isTrue();
    }

    @Test
    void addCumulativeScore() {
        this.status.addCumulativeScore(10);

        assertThat(this.status.getToTalScore()).isEqualTo(20);
    }

    @Test
    void getToTalScore() {
        int totalScore = this.status.getToTalScore();

        assertThat(totalScore).isEqualTo(10);
    }
}
