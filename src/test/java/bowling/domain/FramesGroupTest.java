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

        List<NormalFrame> frames = framesGroup.getFrames();

        assertThat(frames.size()).isEqualTo(1);
    }

    @DisplayName("현재 투구중인 Frame 반환")
    @Test
    public void getCurrentFrame_첫번째() {
        FramesGroup framesGroup = FramesGroup.initiate();
/*
        NormalFrame normalFrame = framesGroup.getCurrentFrame();

        assertThat(normalFrame.getIndex()).isEqualTo(1);*/
    }


}
