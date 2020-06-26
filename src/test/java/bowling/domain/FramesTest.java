package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Frames 클래스 테스트")
class FramesTest {
    final Frames frames = new Frames(new NormalFrame());

    @Test
    void create() {
        assertThat(frames.getCurrentFrame()).isInstanceOf(NormalFrame.class);
    }

    @Test
    void add() {
        frames.add(new NormalFrame());

        assertThat(frames.getFrames()).hasSize(2);
    }

    @Test
    void getCurrentFrameNumber() {
        frames.add(new NormalFrame());

        assertThat(frames.getCurrentFrameNumber()).isEqualTo(2);
    }

    @Test
    void isEndGame() {
        frames.add(new NormalFrame());

        assertThat(frames.isEndGame()).isFalse();
    }
}
