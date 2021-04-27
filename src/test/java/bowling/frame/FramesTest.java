package bowling.frame;

import bowling.domain.frame.Frames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    @Test
    @DisplayName("최대 프레임의 개수는 10개 ")
    void max_frame() {
        Frames frames = new Frames();
        for (int i = 0; i < 12; i++) {
            frames.bowl(10);
        }

        assertThat(frames.getFrames()).hasSize(10);

    }

    @Test
    @DisplayName("점수 계산")
    void calculate() {
        Frames frames = new Frames();
        frames.bowl(5);
        frames.bowl(2);
        frames.bowl(2);
        frames.bowl(2);

        assertThat(frames.getScores()).containsExactly(7, 4);
    }
}
