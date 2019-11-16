package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {
    @Test
    @DisplayName("투구 점수를 반영한다.")
    void addBall() {
        int frameNumber = 1;
        Frame frame = new Frame(frameNumber);
        frame.addBall(5);

        assertThat(frame.getScore()).isEqualTo(5);

        frame.addBall(5);

        assertThat(frame.getScore()).isEqualTo(10);
    }
}
