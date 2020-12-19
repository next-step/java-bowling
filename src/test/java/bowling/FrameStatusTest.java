package bowling;

import bowling.domain.Scoring;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameStatusTest {
    @Test
    void strike() {
        assertThat(new FrameStatus(10, Scoring.STRIKE).toString()).isEqualTo("X");
    }

    @Test
    void spare() {
        assertThat(new FrameStatus(2, Scoring.SPARE).toString()).isEqualTo("2|/");
    }

    @Test
    void miss() {
        assertThat(new FrameStatus(4, 2).toString()).isEqualTo("4|2");
    }


    private static class FrameStatus {
        private final int fallingPins;
        private final Scoring scoring;

        public FrameStatus(int fallingPins, Scoring scoring) {
            this.fallingPins = fallingPins;
            this.scoring = scoring;
        }

        @Override
        public String toString() {
            if (scoring == Scoring.STRIKE) {
                return "X";
            }
            if (scoring == Scoring.SPARE) {
                return String.format("%d|/", fallingPins);
            }
            throw new IllegalStateException();
        }
    }
}
