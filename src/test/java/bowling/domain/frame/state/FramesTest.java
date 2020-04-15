package bowling.domain.frame.state;

import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {
    @DisplayName("첫 NormalFrame을 갖는 Frames 객체를 생성할 수 있다.")
    @Test
    void create() {
        Frames frames = Frames.ofFirst();

        assertThat(frames.getFrames().get(0)).isInstanceOf(NormalFrame.class);
    }
}