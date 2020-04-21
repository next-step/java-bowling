package bowling;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FramesTest {

    @Test
    @DisplayName("프레임생성테스트, 노멀프레임add테스트")
    public void framesTest() {
        Frames frames = new Frames();
        assertThat(frames.currentFrame()).isEqualTo(0);
        Frame frame = new Frame();
        frame.addFrame(new Score(9),false);
        frames.addFrame(frame);
        assertThat(frames.currentFrame()).isEqualTo(1);
    }

    @Test
    @DisplayName("스트라이크처리후 다음프레임여부")
    public void frameOrderTest() {
        Frames frames = new Frames();
        Frame frame = new Frame();
        frame.addFrame(new Score(10),false);
        frames.addFrame(frame);
        assertThat(frames.getFrames().get(0).getState().isFinish()).isTrue();
    }

    @Test
    @DisplayName("처음에스트라이크해도 종료되지않음")
    public void finalFrameTest() {
        Frames frames = new Frames();
        frames.getFrame().addFrame(new Score(10),true);
        frames.addFrame(frames.getFrame());
        assertThat(frames.isFinalGame()).isFalse();
    }

    @Test
    @DisplayName("스페어처리하면종료안됨")
    public void finalFrameSpareTest() {
        Frames frames = new Frames();
        frames.getFrame().addFrame(new Score(5),true);
        frames.getFrame().addFrame(new Score(5),true);
        frames.addFrame(frames.getFrame());
        assertThat(frames.isFinalGame()).isFalse();
    }

    @Test
    @DisplayName("미스하면종료됨")
    public void finalFrameNotSpareTest() {
        Frames frames = new Frames();
        frames.getFrame().addFrame(new Score(5),true);
        frames.getFrame().addFrame(new Score(4),true);
        frames.addFrame(frames.getFrame());
        assertThat(frames.isFinalFrame()).isTrue();
    }

    @Test
    @DisplayName("프레임스코어테스트")
    public void frameScoreTest() {
        Frames frames = new Frames();
        frames.processPin(new Score(4));
        frames.processPin(new Score(5));
        assertThat(frames.getFrameScores().toString()).isEqualTo("9");
    }
}