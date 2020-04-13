package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FramesTest {

    @DisplayName("프레임을 추가한다.")
    @Test
    void addFrame() {
        Frames frames = new Frames();
        frames.addFrame(new NormalFrame(1));
        assertThat(frames.getFrames()).hasSize(1);
    }
}
