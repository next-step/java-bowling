package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    @Test
    void 초기화() {
        assertThat(new Frames().getFrames()).contains(new Frame());
    }

    @Test
    void 스코어_추가() {
        Frames frames = new Frames();
        frames.addScore(0, new Score(1));
        assertThat(frames.getFrames().get(0).getScores()).isEqualTo(new Scores(1));
    }

    @Test
    void 프레임_완료_전() {
        Frames frames = new Frames();
        assertThat(frames.end(0)).isFalse();
    }

    @Test
    void 프레임_완료_후_추가() {
        Frames frames = new Frames(new Frame(new Scores(1, 2)));
        assertThat(frames.end(0)).isTrue();
        assertThat(frames.getFrames()).hasSize(2);
    }
}
