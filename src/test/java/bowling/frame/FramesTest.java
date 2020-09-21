package bowling.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    private Frames frames;
    private Frame frame;

    @BeforeEach
    void setUp() {
        frame = NormalFrame.first();
    }

    @Test
    @DisplayName("Frames 초기화")
    void init() {
        frames = Frames.init();
        assertThat(frames).isNotNull();
    }

    @Test
    @DisplayName("Frames에 Frame 추가")
    void add() {
        frames = Frames.init();
        frames.saveScore(frame);
        assertThat(frames.getFrameNumber()).isEqualTo(1);
    }
}
