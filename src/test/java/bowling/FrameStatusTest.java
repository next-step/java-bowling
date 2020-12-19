package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameStatusTest {
    @Test
    void strike() {
        assertThat(new FrameStatus(10).toString()).isEqualTo("X");
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
