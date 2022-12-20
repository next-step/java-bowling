package bowling.model.frame;

import bowling.model.Pin;
import bowling.model.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    @DisplayName("한 프레임이 끝나면 다음 프레임이 생성된다.")
    void nextFrame() {
        Frames frames = new Frames();
        frames.bowl(Pin.of(10));
        List<Frame> frameList = frames.getFrames();

        assertThat(frameList.get(0).getState()).isInstanceOf(Strike.class);
        assertThat(frames.nextFrame()).isInstanceOf(NormalFrame.class);
    }

    @Test
    @DisplayName("9번 게임 후 마지막 프레임이 생성된다.")
    void finalFrame() {
        Frames frames = new Frames();
        for (int i = 0; i < 9; i++) {
            frames.bowl(Pin.of(10));
            frames.nextFrame();
        }

        assertThat(frames.getCurrentFrame()).isInstanceOf(FinalFrame.class);
    }
}
