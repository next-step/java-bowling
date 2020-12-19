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
        assertThat(new FrameStatus(2, Scoring.SPARE).toString()).isEqualTo("X");
    }

    private class FrameStatus {
        public FrameStatus(int fallingPins) {
        }

        @Override
        public String toString() {
            return "X";
        }
    }
}
