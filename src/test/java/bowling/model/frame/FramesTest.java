package bowling.model.frame;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FramesTest {

    @Test
    void bowling_프레임_마지막_직전_까지_소진() {
        Frames frames = new Frames();
        for (int i = FrameNumber.MIN_FRAME_NUMBER; i < FrameNumber.MAX_FRAME_NUMBER; i++) {
            assertThat(frames.nowFrameNumber()).isEqualTo(i);
            frames.bowling(10);
            assertThat(frames.isFinished()).isFalse();
        }

        assertThat(frames.nowFrameNumber()).isEqualTo(FrameNumber.MAX_FRAME_NUMBER);
    }

    @Test
    void bowling_프레임_모두_소진_시_정상_종료() {
        Frames frames = getBeforeLastFrames();

        assertThat(frames.nowFrameNumber()).isEqualTo(FrameNumber.MAX_FRAME_NUMBER);
        frames.bowling(8);
        frames.bowling(1);
        assertThat(frames.isFinished()).isTrue();
    }

    @Test
    void bowling_프레임_모두_소진_시_정상_마지막_스트라이크() {
        Frames frames = getBeforeLastFrames();

        assertThat(frames.nowFrameNumber()).isEqualTo(FrameNumber.MAX_FRAME_NUMBER);
        frames.bowling(10);
        frames.bowling(10);
        frames.bowling(10);
        assertThat(frames.isFinished()).isTrue();
    }

    @Test
    void bowling_프레임_모두_소진_시_정상_스페어_후_미스() {
        Frames frames = getBeforeLastFrames();

        assertThat(frames.nowFrameNumber()).isEqualTo(FrameNumber.MAX_FRAME_NUMBER);
        frames.bowling(9);
        frames.bowling(1);
        frames.bowling(9);
        assertThat(frames.isFinished()).isTrue();
    }

    @Test
    void bowling_프레임_모두_소진_시_정상_마지막_스페어() {
        Frames frames = getBeforeLastFrames();

        assertThat(frames.nowFrameNumber()).isEqualTo(FrameNumber.MAX_FRAME_NUMBER);
        frames.bowling(9);
        frames.bowling(1);
        frames.bowling(10);
        assertThat(frames.isFinished()).isTrue();
    }

    private Frames getBeforeLastFrames() {
        Frames frames = new Frames();
        for (int i = FrameNumber.MIN_FRAME_NUMBER; i < FrameNumber.MAX_FRAME_NUMBER; i++) {
            frames.bowling(10);
        }
        return frames;
    }
}