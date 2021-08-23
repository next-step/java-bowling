package bowling.domain.frame;

import bowling.domain.pins.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @DisplayName("1부터 10프레임 까지 생성 후 Normal, Final 프레임 확인")
    @Test
    void create_frames() {
        Frames frames = Frames.of();
        while (!frames.isFinish()) {
            frames.bowl(Pins.of(10));
        }

        List<Frame> frameList = frames.getFrames();

        assertThat(frameList).hasSize(10);
        assertThat(frameList.get(0)).isInstanceOf(NormalFrame.class);
        assertThat(frameList.get(8)).isInstanceOf(NormalFrame.class);
        assertThat(frameList.get(9)).isInstanceOf(FinalFrame.class);
    }
}