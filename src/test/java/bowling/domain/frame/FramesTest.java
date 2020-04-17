package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {
    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = Frames.create();
    }

    @DisplayName("NormalFrame, FinalFrame을 갖는 Frames 객체를 생성할 수 있다.")
    @Test
    void createNormalFrame() {
        assertThat(frames.getFrames().get(0)).isInstanceOf(NormalFrame.class);
        assertThat(frames.getFrames().get(9)).isInstanceOf(FinalFrame.class);
        assertThat(frames.getFrames().size()).isEqualTo(10);
    }
}