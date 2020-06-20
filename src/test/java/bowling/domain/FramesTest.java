package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @DisplayName("FramesGroup 정상 생성")
    @Test
    public void initiateFramesGroup_일번_프레임_자동_생성() {
        Frames framesGroup = Frames.initiate();

        List<Frame> frames = framesGroup.getFrames();

        assertThat(frames.size()).isEqualTo(1);
    }

    @DisplayName("현재 투구중인 Frame 반환")
    @Test
    public void getCurrentFrame_첫번째() {
        Frames frames = Frames.initiate();
        frames.bowl(10);

        frames.moveToNextFrame();

        assertThat(frames.getCurrentFrameIndex()).isEqualTo(2);
    }

    @DisplayName("플레이 할 Frame이 있으면 True 반환")
    @Test
    public void hasNextFrame_True() {
        Frames frames = Frames.initiate();
        frames.bowl(9);

        assertThat(frames.hasNextFrame()).isTrue();
    }

    @DisplayName("더이상 플레이할 프레임이 없으면 false 반환")
    @Test
    public void hasNextFrame_False() {
        Frames frames = Frames.initiate();
        for (int i = 0; i < 11; i++) {
            frames.bowl(10);
            frames.moveToNextFrame();
        }

        assertThat(frames.hasNextFrame()).isFalse();
    }
}
