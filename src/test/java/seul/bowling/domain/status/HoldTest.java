package seul.bowling.domain.status;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HoldTest {
    @Test
    void addPins() {
        Status status = new Hold(1);

        status = status.addPins(9);

        assertThat(status.getClass()).isEqualTo(Spare.class);
        assertThat(status.getToTalScore()).isEqualTo(10);
    }

    @Test
    void addCumulativeScore() {
        Status status = new Hold(1);

        status.addCumulativeScore(10);

        assertThat(status.getToTalScore()).isEqualTo(11);
    }

    @Test
    void getToTalScore() {
        Status status = new Hold(9);
        status.addPins(1);

        assertThat(status.getToTalScore()).isEqualTo(10);
    }

    @Test
    void equalsStatus() {
        Status status = new Hold(9);

        assertThat(status.equalsStatus(new Hold(10))).isTrue();

    }
}
