package bowling.domain.frame;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FramesTest {

    @Test
    void addPin_WhenFinished_ThrowsIllegalStateException() {
        Frames fullFrames = createFullFrames();

        assertThatThrownBy(() -> fullFrames.addPin(2))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void addPin_Adds9NormalFramesAnd1FinalFrame() {
        Frames fullFrames = createFullFrames();

        List<Frame> frames = fullFrames.getFrames();
        for (int i = 0; i < frames.size() - 1; i++) {
            assertThat(frames.get(i)).isInstanceOf(NormalFrame.class);
        }
        assertThat(frames.get(frames.size() - 1)).isInstanceOf(FinalFrame.class);
    }

    @Test
    void isFinished() {
        assertThat(new Frames(10).isFinished()).isFalse();
        assertThat(createFullFrames().isFinished()).isTrue();
    }

    private Frames createFullFrames() {
        Frames frames = new Frames(7);
        frames.addPin(2);

        for (int i = 0; i < 9; i++) {
            frames.addPin(7);
            frames.addPin(2);
        }
        return frames;
    }

    @Test
    void currentFrame() {
        Frames frames = new Frames(7);

        assertThat(frames.currentFrame()).isEqualTo(1);
        frames.addPin(2);
        assertThat(frames.currentFrame()).isEqualTo(2);
    }
}
