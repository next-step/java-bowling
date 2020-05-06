package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.MockFrame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.score.MockFrameScore;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    void ofDefaultInit() {
        Frames ofNormalInitFrames = Frames.ofDefaultInit();
        assertThat(ofNormalInitFrames.getFrames())
                .containsExactly(NormalFrame.init());
    }

    @Test
    void getCurrentFrameNumber() {
        Frame frame1 = new MockFrame(1);
        Frame frame2 = new MockFrame(2);
        Frames frames = new Frames(frame1, frame2);
        assertThat(frames.getCurrentFrameNumber())
                .isEqualTo(2);
    }

    @Test
    void shot() {
        MockFrame frame1 = new MockFrame(1, true);

        Frames frames = new Frames(frame1);
        frames.shot(5);

        assertThat(frames.getCurrentFrameNumber())
                .isEqualTo(2);
        assertThat(frame1.getShotsCount())
                .isEqualTo(1);
    }

    @Test
    void isGameSet() {
        Frame finalFrame = FinalFrame.of();
        Frames frames = new Frames(finalFrame);
        assertThat(frames.isGameSet())
                .isFalse();

        finalFrame.shot(3);
        finalFrame.shot(3);

        assertThat(frames.isGameSet())
                .isTrue();
    }

    @Test
    void getCurrentFrameShotCount() {
        Frame frame1 = new MockFrame(1, 2);
        Frame frame2 = new MockFrame(2, 1);

        Frames frames = new Frames(frame1, frame2);

        assertThat(frames.getCurrentFrameShotCount())
                .isEqualTo(1);
    }

    @Test
    void getScores() {
        Frame frame1 = new MockFrame(true, new MockFrameScore(true, 25));
        Frame frame2 = new MockFrame(true, new MockFrameScore(false, 15));
        Frame frame3 = new MockFrame(false, new MockFrameScore(false, 5));

        Frames frames = new Frames(frame1, frame2, frame3);
        assertThat(frames.getScores())
                .hasSize(1)
                .containsExactly(25);
    }
}
