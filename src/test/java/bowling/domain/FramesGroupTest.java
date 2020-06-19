package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FramesGroupTest {

    @DisplayName("FramesGroup 정상 생성")
    @Test
    public void initiateFramesGroup_일번_프레임_자동_생성() {
        FramesGroup framesGroup = FramesGroup.initiate();

        List<Frame> frames = framesGroup.getFrames();

        assertThat(frames.size()).isEqualTo(1);
    }

    @DisplayName("현재 투구중인 Frame 반환")
    @Test
    public void getCurrentFrame_첫번째() {
        FramesGroup framesGroup = FramesGroup.initiate();
        framesGroup.bowl(10);

        NormalFrame normalFrame = (NormalFrame) framesGroup.getCurrentFrame();

        assertThat(normalFrame.getIndex()).isEqualTo(2);
    }

    @DisplayName("플레이 할 Frame이 있으면 True 반환")
    @Test
    public void hasNextFrame_True() {
        FramesGroup framesGroup = FramesGroup.initiate();
        framesGroup.bowl(9);

        assertThat(framesGroup.hasNextFrame()).isTrue();
    }

    @DisplayName("더이상 플레이할 프레임이 없으면 false 반환")
    @Test
    public void hasNextFrame_False() {
        FramesGroup framesGroup = FramesGroup.initiate();
        for (int i = 0; i < 11; i++) {
            framesGroup.bowl(10);
            framesGroup.getCurrentFrame();
        }

        assertThat(framesGroup.hasNextFrame()).isFalse();
    }
}
