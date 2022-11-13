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
}
