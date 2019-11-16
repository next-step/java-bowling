package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    @Test
    @DisplayName("투구 할 프레임을 리턴한다.")
    void nextFrame() {
        Frames defaultFrames = new Frames();
        Frame frame = defaultFrames.nextFrame();
        assertThat(frame.getFrameNumber()).isEqualTo(1);

        Frames testFrames = getTestFrames();
        Frame expectedFrame = testFrames.nextFrame();
        assertThat(expectedFrame.getFrameNumber()).isEqualTo(2);
    }

    private Frames getTestFrames() {
        List<Frame> frameList = new ArrayList<>();
        Frame frame = new Frame(1) {
            @Override
            public boolean isAddAble() {
                return false;
            }
        };
        frameList.add(frame);
        return new Frames(frameList);
    }
}
