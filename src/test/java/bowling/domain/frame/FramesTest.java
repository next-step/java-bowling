package bowling.domain.frame;

import bowling.domain.bowling.BowlingPinsGroup;
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
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();
        Frame frame = frames.getCurrentFrame();
        frame.bowl(10, bowlingPinsGroup);

        frames.moveToNextFrame(frame);

        assertThat(frames.getCurrentFrame().getIndex()).isEqualTo(2);
    }

    @DisplayName("플레이 할 Frame이 있으면 True 반환")
    @Test
    public void hasNextFrame_True() {
        Frames frames = Frames.initiate();
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();
        Frame frame = frames.getCurrentFrame();
        frame.bowl(10, bowlingPinsGroup);

        assertThat(frames.hasNextFrame()).isTrue();
    }

/*    @DisplayName("더이상 플레이할 프레임이 없으면 false 반환")
    @Test
    public void hasNextFrame_False() {
        Frames frames = Frames.initiate();
        for (int i = 0; i < 12; i++) {
            BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();
            frames.bowl(10, bowlingPinsGroup);
            frames.moveToNextFrame();
        }

        assertThat(frames.hasNextFrame()).isFalse();
    }*/
}
