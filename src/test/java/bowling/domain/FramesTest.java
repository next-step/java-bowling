package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    @Test
    void 초기화() {
        assertThat(new Frames().getFrames()).contains(new NormalFrame());
    }

    @Test
    void 스코어_추가() {
        Frames frames = new Frames();
        frames.addRoll(new Pin(1));
        assertThat(frames.getFrames().get(0).getScores()).isEqualTo(new Rolls(1));
    }

    @Test
    void 프레임_완료_전() {
        Frames frames = new Frames();
        assertThat(frames.isEnd(0)).isFalse();
    }

    @Test
    void 프레임_완료_후_프레임_추가() {
        Frames frames = new Frames(new NormalFrame(new Rolls(1, 2), FrameStatus.MISS));
        assertThat(frames.isEnd(0)).isTrue();
        assertThat(frames.getFrames()).hasSize(2);
    }
}
