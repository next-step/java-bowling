package bowling;

import bowling.domain.FinalFrame;
import bowling.domain.Frames;
import bowling.domain.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FramesTest {

    @Test
    @DisplayName("프레임생성테스트, 노멀프레임add테스트")
    public void framesTest() {
        Frames frames = new Frames();
        assertThat(frames.currentFrame()).isEqualTo(0);
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.add(10);
        frames.addNormalFrame(normalFrame);
        assertThat(frames.currentFrame()).isEqualTo(1);
    }

    @Test
    @DisplayName("9프레임여부테스트")
    public void frameOrderTest() {
        Frames frames = new Frames();
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.add(10);
        frames.addNormalFrame(normalFrame);
        assertThat(frames.isEndNormalFrame()).isFalse();
    }

    @Test
    @DisplayName("처음에스트라이크해도 종료되지않음")
    public void finalFrameTest() {
        Frames frames = new Frames();
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.add(10);
        frames.addFinalFrame(finalFrame);
        assertThat(frames.isEndFinalFrame()).isFalse();
    }

    @Test
    @DisplayName("스페어처리하면종료안됨")
    public void finalFrameSpareTest() {
        Frames frames = new Frames();
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.add(5);
        finalFrame.add(5);
        frames.addFinalFrame(finalFrame);
        assertThat(frames.isEndFinalFrame()).isFalse();
    }

    @Test
    @DisplayName("스페어처리하면종료안됨")
    public void finalFrameNotSpareTest() {
        Frames frames = new Frames();
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.add(5);
        finalFrame.add(4);
        frames.addFinalFrame(finalFrame);
        assertThat(frames.isEndFinalFrame()).isTrue();
    }
}