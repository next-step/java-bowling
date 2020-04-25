package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    void getCurrentFrameNumber() {
        Frames frames = new Frames();
        assertThat(frames.getCurrentFrameNumber())
                .isEqualTo(1);

        frames.shot(10);
        assertThat(frames.getCurrentFrameNumber())
                .isEqualTo(2);

        frames.shot(5);
        assertThat(frames.getCurrentFrameNumber())
                .isEqualTo(2);
    }

    @Test
    void shot() {
        Frames frames = new Frames();
        frames.shot(5);

        assertThat(frames.getFrames())
                .anyMatch(v -> v.shotScores().get(0).singleScore() == 5)
                .hasSize(1);
    }

    @Test
    void isGameSet() {
        Frames frames = new Frames();
        for (int i = 0; i < 10; i++) {
            frames.shot(10);
        }
        assertThat(frames.isGameSet())
                .isFalse();
        frames.shot(4);
        frames.shot(5);
        assertThat(frames.isGameSet())
                .isTrue();
    }

    @Test
    void getCurrentFrameShotCount(){
        Frames frames = new Frames();
        assertThat(frames.getCurrentFrameShotCount())
                .isEqualTo(0);
        frames.shot(4);
        assertThat(frames.getCurrentFrameShotCount())
                .isEqualTo(1);

        frames.shot(2);
        assertThat(frames.getCurrentFrameShotCount())
                .isEqualTo(0);

        frames.shot(10);
        assertThat(frames.getCurrentFrameShotCount())
                .isEqualTo(0);
    }
}
